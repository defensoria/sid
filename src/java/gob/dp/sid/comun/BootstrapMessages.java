package gob.dp.sid.comun;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIMessages;
import javax.faces.context.FacesContext;
import javax.faces.render.FacesRenderer;
import javax.faces.context.ResponseWriter;

import com.sun.faces.renderkit.Attribute;
import com.sun.faces.renderkit.AttributeManager;
import com.sun.faces.renderkit.RenderKitUtils;
import com.sun.faces.renderkit.html_basic.MessagesRenderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage.Severity;

/**
 * Component for twitter bootstrap alerts.
 * Overrides default JSF Message renderer with Bootstrap alert design.
 * 
 * @author vlcekmi3
 */
@FacesRenderer(componentFamily="javax.faces.Messages", rendererType="javax.faces.Messages")
public class BootstrapMessages extends MessagesRenderer {
    
    private static final Attribute[] ATTRIBUTES = AttributeManager.getAttributes(AttributeManager.Key.MESSAGESMESSAGES);

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        rendererParamsNotNull(context, component);

        if (!shouldEncode(component)) return;
        

        boolean mustRender = shouldWriteIdAttribute(component);

        UIMessages messages = (UIMessages) component;
        ResponseWriter writer = context.getResponseWriter();
        assert(writer != null);

        String clientId = ((UIMessages) component).getFor();
        if (clientId == null)
            if (messages.isGlobalOnly())
                clientId = "";

        Iterator messageIter = getMessageIter(context, clientId, component);

        assert(messageIter != null);
        if (!messageIter.hasNext()) {
            if (mustRender) {
                if ("javax_faces_developmentstage_messages".equals(component.getId())) {
                    return;
                }
                writer.startElement("div", component);
                writeIdAttributeIfNecessary(context, writer, component);
                writer.endElement("div");
            }
            return;
        }

        writeIdAttributeIfNecessary(context, writer, component);

        // style is rendered as a passthru attribute
        RenderKitUtils.renderPassThruAttributes(context, writer, component, ATTRIBUTES);

        Map<Severity, List<FacesMessage>> msgs = new HashMap<Severity, List<FacesMessage>>();
        msgs.put(FacesMessage.SEVERITY_INFO, new ArrayList<FacesMessage>()); // Bootstrap info
        msgs.put(FacesMessage.SEVERITY_WARN, new ArrayList<FacesMessage>()); // Bootstrap warning
        msgs.put(FacesMessage.SEVERITY_ERROR, new ArrayList<FacesMessage>()); // Bootstrap error
        msgs.put(FacesMessage.SEVERITY_FATAL, new ArrayList<FacesMessage>()); // Bootstrap error

        while (messageIter.hasNext()) {
            FacesMessage curMessage = (FacesMessage) messageIter.next();
            
            if (curMessage.isRendered() && !messages.isRedisplay()) {
                continue;
            }
            msgs.get(curMessage.getSeverity()).add(curMessage);
        }
            
        List<FacesMessage> severityMessages = msgs.get(FacesMessage.SEVERITY_FATAL);
        if (severityMessages.size() > 0){
            encodeLoadingInfo(context, component, messages, FacesMessage.SEVERITY_FATAL, severityMessages);
        }
        
        severityMessages = msgs.get(FacesMessage.SEVERITY_ERROR);
        if (severityMessages.size() > 0){
            encodeLoadingInfo(context, component, messages, FacesMessage.SEVERITY_ERROR, severityMessages);
        }
        
        severityMessages = msgs.get(FacesMessage.SEVERITY_WARN);
        if (severityMessages.size() > 0){
            encodeLoadingInfo(context, component, messages, FacesMessage.SEVERITY_WARN, severityMessages);
        }
        
        severityMessages = msgs.get(FacesMessage.SEVERITY_INFO);
        if (severityMessages.size() > 0){
            encodeLoadingInfo(context, component, messages, FacesMessage.SEVERITY_INFO, severityMessages);
        }
    }
        
    private void encodeSeverityMessages(FacesContext facesContext, UIComponent component, UIMessages uiMessages, Severity severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        
        String alertSeverityClass = "";
        
        if(FacesMessage.SEVERITY_INFO.equals(severity)) {
            alertSeverityClass = "alert-info";
        } else if(FacesMessage.SEVERITY_WARN.equals(severity)) {
            alertSeverityClass = ""; // Default alert is a warning
        } else if(FacesMessage.SEVERITY_ERROR.equals(severity)) {
            alertSeverityClass = "alert-error";
        } else if(FacesMessage.SEVERITY_FATAL.equals(severity)) {
            alertSeverityClass = "alert-error";
        }
        
        writer.startElement("div", null);
        writer.writeAttribute("class", "alert " + alertSeverityClass, "alert " + alertSeverityClass);
        writer.startElement("a", component);
        writer.writeAttribute("class", "close", "class");
        writer.writeAttribute("data-dismiss", "alert", "data-dismiss");
        writer.writeAttribute("href", "#", "href");
        writer.write("&times;");
        writer.endElement("a");
        
        writer.startElement("ul", null);
        
        for (FacesMessage msg : messages){
            String summary = msg.getSummary() != null ? msg.getSummary() : "";
            String detail = msg.getDetail() != null ? msg.getDetail() : summary;
            
            writer.startElement("li", component);

            if (uiMessages.isShowSummary()) {
                writer.startElement("strong", component);
                writer.writeText(summary, component, null);
                writer.endElement("strong");
            }
            
            if (uiMessages.isShowDetail()) {
                writer.writeText(" " + detail, null);
            }
            
            writer.endElement("li");
            msg.rendered();
        }
        writer.endElement("ul");
        writer.endElement("div");
    }

    
    
    private void encodeLoadingInfo(FacesContext facesContext, UIComponent component, UIMessages uiMessages, Severity severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();
        
        if(FacesMessage.SEVERITY_WARN.equals(severity)) {
            writer.append("<div class=\"modal fade\" id=\"idAlertaServer\">");
        } else if(FacesMessage.SEVERITY_INFO.equals(severity)) {
            writer.append("<div class=\"modal fade\" id=\"idInfoServer\">");
        } else if(FacesMessage.SEVERITY_ERROR.equals(severity)) {
            writer.append("<div class=\"modal fade\" id=\"idErrorServer\">");
        }    
        writer.append("<div class=\"modal-dialog modal-sm\" >");
        if(FacesMessage.SEVERITY_WARN.equals(severity)) {
            writer.append("<div class=\"modal-content\" style=\" background: #FCF8E3\">");
        } else if(FacesMessage.SEVERITY_INFO.equals(severity)) {
            writer.append("<div class=\"modal-content\" style=\" background: #D9EDF7\">");
        } else if(FacesMessage.SEVERITY_ERROR.equals(severity)) {
            writer.append("<div class=\"modal-content\" style=\" background: #F2DEDE\">");
        }
        writer.append("<div class=\"modal-header\" style=\"padding: 5px;\">");
        writer.append("<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\"><span style=\"color: black; font-size: large\" ></span></button>");
        writer.append("<h4 style=\"color: #8A6D3B\"><b>"+messages.get(0).getDetail()+"</b></h4>");
        writer.append("</div>");
        writer.append("<div style=\"background: white;box-sizing: border-box;\" >");
        writer.append("<div class=\"modal-body\" style=\"padding: 7px;\">");
        if(FacesMessage.SEVERITY_WARN.equals(severity)) {
            writer.append("<table style=\"width: 100%\"><tr><td style=\"padding-right: 8px\"><img style=\"width: 30px\" src=\"../../bootstrap/img/warning.png\" /></td><td><div style=\"font-size: 1.2em\" class=\"text-resalt-comun\"><b>"+messages.get(0).getSummary()+"</b></div></td></tr></table>");
        } else if(FacesMessage.SEVERITY_INFO.equals(severity)) {
            writer.append("<table style=\"width: 100%\"><tr><td style=\"padding-right: 8px\"><img style=\"width: 30px\" src=\"../../bootstrap/img/info.png\" /></td><td><div style=\"font-size: 1.2em\" class=\"text-resalt-comun\"><b>"+messages.get(0).getSummary()+"</b></div></td></tr></table>");
        } else if(FacesMessage.SEVERITY_ERROR.equals(severity)) {
            writer.append("<table style=\"width: 100%\"><tr><td style=\"padding-right: 8px\"><img style=\"width: 30px\" src=\"../../bootstrap/img/info.png\" /></td><td><div style=\"font-size: 1.2em\" class=\"text-resalt-comun\"><b>"+messages.get(0).getSummary()+"</b></div></td></tr></table>");
        }
        
        writer.append("</div></div></div></div></div>");     
        
        if(FacesMessage.SEVERITY_WARN.equals(severity)) {
            writer.append("<script>\n" +
"            function alertarServer() {\n" +
"                $(\"#idAlertaServer\").modal('show');\n "
                + "}"
                + "alertarServer();" +
"            </script>");
        } else if(FacesMessage.SEVERITY_INFO.equals(severity)) {
            writer.append("<script>\n" +
"            function informarServer() {\n" +
"                $(\"#idInfoServer\").modal('show');\n "
                + "}"
                + "informarServer();" +
"            </script>");
        }else if(FacesMessage.SEVERITY_ERROR.equals(severity)) {
            writer.append("<script>\n" +
"            function errorServer() {\n" +
"                $(\"#idErrorServer\").modal('show');\n "
                + "}"
                + "errorServer();" +
"            </script>");
        }
           
                            
    }
}
    