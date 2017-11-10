/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.sid.reporte.controller;

import gob.dp.sid.administracion.seguridad.controller.LoginController;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.comun.controller.AbstractManagedBean;
import gob.dp.sid.registro.entity.Expediente;
import gob.dp.sid.registro.service.ExpedienteService;
import gob.dp.sid.reporte.entity.Reporte;
import gob.dp.sid.reporte.entity.Reporte001;
import gob.dp.sid.reporte.entity.ReporteContenedor;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author carlos
 */
@Named
@Scope("session")
public class ReporteController extends AbstractManagedBean implements Serializable {

    private List<Expediente> listaExpedienteXUsuario;

    private String grafico002;

    private Usuario usuarioSession;

    private List<Reporte> listaReporte001;
    
    private List<ReporteContenedor> contenedors;

    JasperPrint jasperPrint;

    @Autowired
    private ExpedienteService expedienteService;

    public String inicio() {
        usuarioSession();
        listaExpedienteXUsuario = expedienteService.expedienteBuscarUsuario(usuarioSession.getCodigo());
        cargarGraficos002();
        cargarGraficos002B();
        cargarGraficos002C();
        return "expedienteReporte";
    }

    private void cargarGraficos002() {
        StringBuilder sb = new StringBuilder();
        int quejax = 0;
        int petitoriox = 0;
        int consultax = 0;
        int quejay = 0;
        int petitorioy = 0;
        int consultay = 0;
        Reporte r = new Reporte();
        for (Expediente e : listaExpedienteXUsuario) {

            if (e.getTipoClasificion().equals("01")) {
                if (e.getGeneral().equals("C")) {
                    quejay++;
                } else {
                    quejax++;
                }
            }
            if (e.getTipoClasificion().equals("02")) {
                if (e.getGeneral().equals("C")) {
                    consultay++;
                } else {
                    consultax++;
                }
            }
            if (e.getTipoClasificion().equals("03")) {
                if (e.getGeneral().equals("C")) {
                    petitorioy++;
                } else {
                    petitoriox++;
                }
            }
        }
        sb.append("{x: 'Queja', y: " + quejax + ", z: " + quejay + "},");
        sb.append("{x: 'Petitorio', y: " + petitoriox + ", z: " + petitorioy + "},");
        sb.append("{x: 'Consulta', y: " + consultax + ", z: " + consultay + "}");
        grafico002 = sb.toString();
    }

    private void cargarGraficos002B() {
        List<Expediente> es = expedienteService.expedienteBuscarUsuarios(new Expediente());
        listaReporte001 = new ArrayList<>();
        if (es.size() > 0) {
            for (Expediente e2 : es) {
                listaExpedienteXUsuario = expedienteService.expedienteBuscarUsuario(e2.getUsuarioRegistro());
                int quejax = 0;
                int petitoriox = 0;
                int consultax = 0;
                int quejay = 0;
                int petitorioy = 0;
                int consultay = 0;
                Reporte r = new Reporte();
                for (Expediente e : listaExpedienteXUsuario) {

                    if (e.getTipoClasificion().equals("01")) {
                        if (e.getGeneral().equals("C")) {
                            quejay++;
                        } else {
                            quejax++;
                        }
                    }
                    if (e.getTipoClasificion().equals("02")) {
                        if (e.getGeneral().equals("C")) {
                            consultay++;
                        } else {
                            consultax++;
                        }
                    }
                    if (e.getTipoClasificion().equals("03")) {
                        if (e.getGeneral().equals("C")) {
                            petitorioy++;
                        } else {
                            petitoriox++;
                        }
                    }
                }
                r.setUsuario(e2.getUsuarioCompleto());
                r.setConsultaTramite(consultax);
                r.setConsultaConcluido(consultay);
                r.setPetitorioTramite(petitoriox);
                r.setPetitorioConcluido(petitorioy);
                r.setQuejaTramite(quejax);
                r.setQuejaConcluido(quejay);
                listaReporte001.add(r);
            }
        }
    }
    
    public void limpiarComisionado(){
        System.out.println("limpiar");
    }
    
    private void cargarGraficos002C() {
                listaExpedienteXUsuario = expedienteService.expedienteBuscarUsuario(usuarioSession.getCodigo());
                contenedors = new ArrayList<>();
                ReporteContenedor contenedor = new ReporteContenedor();
                List<Reporte001> reporte001s = new ArrayList<>();
                int quejax = 0;
                int petitoriox = 0;
                int consultax = 0;
                int quejay = 0;
                int petitorioy = 0;
                int consultay = 0;
   
                for (Expediente e : listaExpedienteXUsuario) {

                    if (e.getTipoClasificion().equals("01")) {
                        if (e.getGeneral().equals("C")) {
                            quejay++;
                        } else {
                            quejax++;
                        }
                    }
                    if (e.getTipoClasificion().equals("02")) {
                        if (e.getGeneral().equals("C")) {
                            consultay++;
                        } else {
                            consultax++;
                        }
                    }
                    if (e.getTipoClasificion().equals("03")) {
                        if (e.getGeneral().equals("C")) {
                            petitorioy++;
                        } else {
                            petitoriox++;
                        }
                    }
                }
                reporte001s.add(new Reporte001("Queja Activa", quejax));
                reporte001s.add(new Reporte001("Queja Concluida", quejay));
                reporte001s.add(new Reporte001("Consulta Activa", consultax));
                reporte001s.add(new Reporte001("Consulta Concluida", consultay));
                reporte001s.add(new Reporte001("Petitorio Activo", petitoriox));
                reporte001s.add(new Reporte001("Petitorio Concluido", petitorioy));
                contenedor.setReporte001s(reporte001s);
                contenedor.setReportes(listaReporte001);
                contenedor.setTitulo("CARGA DE COMISIONADOS");
                contenedor.setFecha("27/10/1981");
                contenedors.add(contenedor);

    }

    private void usuarioSession() {
        usuarioSession = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        LoginController loginController = (LoginController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "loginController");
        usuarioSession = loginController.getUsuarioSesion();
    }

    public void initJasper() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                listaReporte001);
        //jasperPrint = JasperFillManager.fillReport(RutaType.URL_FILE_SYSTEM.getValue()+"reporteExpediente001.jasper", new HashMap(), beanCollectionDataSource);
    }

    public void pdf() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasper();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_caso.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }
    
    public void initJasper2() throws JRException {
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(contenedors);
        //jasperPrint = JasperFillManager.fillReport(RutaType.URL_FILE_SYSTEM.getValue()+"reporteExpediente001Total.jasper", new HashMap(), beanCollectionDataSource);

    }

    public void pdf2() throws JRException, IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = simpleDateFormat.format(date);
        initJasper2();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletResponse httpServletResponse;
        httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=" + fecha + "_caso.pdf");
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
        facesContext.responseComplete();
        facesContext.renderResponse();
    }

    /*getter and setter*/
    public List<Expediente> getListaExpedienteXUsuario() {
        return listaExpedienteXUsuario;
    }

    public void setListaExpedienteXUsuario(List<Expediente> listaExpedienteXUsuario) {
        this.listaExpedienteXUsuario = listaExpedienteXUsuario;
    }

    public String getGrafico002() {
        return grafico002;
    }

    public void setGrafico002(String grafico002) {
        this.grafico002 = grafico002;
    }

    public Usuario getUsuarioSession() {
        return usuarioSession;
    }

    public void setUsuarioSession(Usuario usuarioSession) {
        this.usuarioSession = usuarioSession;
    }

    public List<Reporte> getListaReporte001() {
        return listaReporte001;
    }

    public void setListaReporte001(List<Reporte> listaReporte001) {
        this.listaReporte001 = listaReporte001;
    }

    public List<ReporteContenedor> getContenedors() {
        return contenedors;
    }

    public void setContenedors(List<ReporteContenedor> contenedors) {
        this.contenedors = contenedors;
    }

}
