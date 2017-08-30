package gob.dp.sid.administracion.seguridad.controller;

import gob.dp.sid.administracion.parametro.controller.CatalogoController;
import gob.dp.sid.bandeja.controller.BandejaController;
import gob.dp.sid.comun.controller.BusquedaController;
import gob.dp.sid.comun.entity.Menu;
import gob.dp.sid.comun.service.MenuService;
import gob.dp.sid.registro.controller.ImportarController;
import gob.dp.sid.registro.controller.RegistroController;
import gob.dp.sid.reporte.controller.ReporteSidController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Administrador
 */
@Named
@Scope("session")
public class MenuController implements Serializable {

    private List<Menu> menuPadre;

    private List<Menu> menuHijo;

    private List<Menu> menuNieto;

    @Autowired
    private MenuService menuService;

    public void cargarMenu() {
        cargarBusquedaGeneral();
        menuPadre = menuService.menuPadre();
        FacesContext context = FacesContext.getCurrentInstance();
        SeguridadUtilController seguridadUtilController = (SeguridadUtilController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "seguridadUtilController");
        if (!seguridadUtilController.tieneRecurso("REC_CAV001")) {
            menuPadre.remove(5);
        }
        if (!seguridadUtilController.tieneRecurso("REC_ONP001")) {
            menuPadre.remove(4);
        }
        if (!seguridadUtilController.tieneRecurso("REC_MAN001")) {
            menuPadre.remove(1);
        }
        if (!seguridadUtilController.tieneRecurso("REC_SEG001")) {
            menuPadre.remove(0);
        }
        cargarPagina(0);
    }

    private void cargarBusquedaGeneral() {
        FacesContext context = FacesContext.getCurrentInstance();
        BusquedaController busquedaController = (BusquedaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaController");
        busquedaController.inicioBusqueda();
    }

    public String inicio() {
        cargarPagina(0);
        return "ingresarSistema";
    }

    private void cargarMensajesPendientes() {
        FacesContext context = FacesContext.getCurrentInstance();
        BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
        bandejaController.cargarMensajesPendientes();
    }

    public String cargarPagina(int codigoPagina) {
        menuHijo = null;
        menuNieto = new ArrayList<>();
        cargarMensajesPendientes();
        FacesContext context = FacesContext.getCurrentInstance();

        if (codigoPagina == 0) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            registroController.inicio();
            return null;
        }

        if (codigoPagina == 1) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(1);
            return registroController.cargarNuevoExpediente();
        }

        if (codigoPagina == 2) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(1);
            return registroController.cargarNuevoExpediente();
        }

        if (codigoPagina == 3) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(1);
            return registroController.cargarNuevaPersona();
        }

        if (codigoPagina == 4) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(1);
            return registroController.cargarNuevaEntidad();
        }

        if (codigoPagina == 5) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(1);
            return registroController.cargarBusquedaExpediente();
        }

        if (codigoPagina == 6) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            menuHijo = menuService.menuHijo(1);
            return registroController.cargarExpedienteGestionLista();
        }

        if (codigoPagina == 12) {

            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            menuHijo = menuService.menuHijo(12);
            return busquedaUsuarioController.cargarPagina();
        }

        if (codigoPagina == 12) {

            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            menuHijo = menuService.menuHijo(12);
            return busquedaUsuarioController.cargarPagina();
        }

        if (codigoPagina == 12) {

            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            menuHijo = menuService.menuHijo(12);
            return busquedaUsuarioController.cargarPagina();
        }

        if (codigoPagina == 13) {
            BusquedaUsuarioController busquedaUsuarioController = (BusquedaUsuarioController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "busquedaUsuarioController");
            menuHijo = menuService.menuHijo(12);
            return busquedaUsuarioController.cargarPagina();
        }

        if (codigoPagina == 14) {
            RolController rolController = (RolController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "rolController");
            menuHijo = menuService.menuHijo(12);
            return rolController.cargarPagina();
        }

        if (codigoPagina == 15) {
            CatalogoController catalogoController = (CatalogoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "catalogoController");
            menuHijo = menuService.menuHijo(15);
            return catalogoController.cargarPagina();
        }

        if (codigoPagina == 16) {
            ReporteSidController reporteSidController = (ReporteSidController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "reporteSidController");
            //menuHijo = menuService.menuHijo(15);
            return reporteSidController.cargarPaginaExpedienteReporte();
        }

        if (codigoPagina == 17) {
            RolController rolController = (RolController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "rolController");
            //menuHijo = menuService.menuHijo(15);
            return rolController.cargarGrafica();
        }

        if (codigoPagina == 18) {
            BandejaController bandejaController = (BandejaController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "bandejaController");
            return bandejaController.cargarBandeja();
        }

        if (codigoPagina == 19) {
            ImportarController importarController = (ImportarController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "importarController");
            return importarController.cargarPagina();
        }

        if (codigoPagina == 33) {
            CatalogoController catalogoController = (CatalogoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "catalogoController");
            menuHijo = menuService.menuHijo(15);
            return catalogoController.cargarPagina();
        }

        if (codigoPagina == 34) {
            CatalogoController catalogoController = (CatalogoController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "catalogoController");
            menuHijo = menuService.menuHijo(15);
            return catalogoController.cargarPaginaDerivados();
        }
        
        if (codigoPagina == 35) {
            RegistroController registroController = (RegistroController) context.getELContext().getELResolver().getValue(context.getELContext(), null, "registroController");
            return registroController.cargarFormularioVirtual();
        }

        if (codigoPagina == 32) {
            menuHijo = menuService.menuHijo(8);
            return "contexto";
        }

        if (codigoPagina == 27) {
            menuHijo = menuService.menuHijo(27);
            return null;
        }
        return null;
    }

    public List<Menu> getMenuPadre() {
        return menuPadre;
    }

    public void setMenuPadre(List<Menu> menuPadre) {
        this.menuPadre = menuPadre;
    }

    public List<Menu> getMenuHijo() {
        return menuHijo;
    }

    public void setMenuHijo(List<Menu> menuHijo) {
        this.menuHijo = menuHijo;
    }

    public List<Menu> getMenuNieto() {
        return menuNieto;
    }

    public void setMenuNieto(List<Menu> menuNieto) {
        this.menuNieto = menuNieto;
    }
}
