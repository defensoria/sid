package gob.dp.sid.administracion.seguridad.controller;

import java.util.ArrayList;
import java.util.List;

import gob.dp.sid.administracion.seguridad.entity.Rol;
import gob.dp.sid.administracion.seguridad.entity.Usuario;
import gob.dp.sid.administracion.seguridad.service.RolService;
import gob.dp.sid.administracion.seguridad.service.UsuarioService;
import javax.inject.Named;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class RolController {

    private static final Logger log = Logger.getLogger(RolController.class);

    @Autowired
    private RolService rolService;

    @Autowired
    private UsuarioService usuarioService;
    
    private List<Rol> listaRol;

    private String graficaUsuarios;
    
    public String cargarGrafica() {
        construirGrafica();
        return "usuarioRoles";
    }
    
    private void construirGrafica(){
        List<Usuario> usuariosGrafica = usuarioService.buscarUsuarioTotal();
        StringBuilder sb = new StringBuilder();
        int nivel1 = 0;
        int nivel2 = 0;
        int nivel3 = 0;
        for(Usuario u : usuariosGrafica){
            String sex= "";
            if(u.getSexo().equals("M")){
                sex = "male.png";
            }else{
                sex = "female.png";
            }
             
            if(u.getNivel() == 1){
                nivel1++;
                sb.append("var "+u.getCodigo()+" = member(300, "+100*u.getNivel()+", 'Defensor', '"+u.getNombre()+"_"+u.getApellidoPaterno()+"', '"+sex+"', '#30d0c6'); ");
            }
            if(u.getNivel() == 2){
                nivel2++;
                sb.append("var "+u.getCodigo()+" = member("+100*nivel2*2+", "+100*u.getNivel()+", 'Adjunto', '"+u.getNombre()+"_"+u.getApellidoPaterno()+"', '"+sex+"', '#30d0c6'); ");
            }
            if(u.getNivel() == 3){
                nivel3++;
                sb.append("var "+u.getCodigo()+" = member("+100*nivel3*2+", "+100*u.getNivel()+", 'Comisionado', '"+u.getNombre()+"_"+u.getApellidoPaterno()+"', '"+sex+"', '#30d0c6'); ");
            }    
                
            
            
        }
        graficaUsuarios = sb.toString();
        /*
        
                    var homer = member(90, 200, 'VP Marketing', 'Homer Simpson', 'male.png', '#7c68fd', '#f1f1f1');
                    var marge = member(300, 200, 'VP Sales', 'Marge Simpson', 'female.png', '#7c68fd', '#f1f1f1');
                    var lisa = member(500, 200, 'VP Production', 'Lisa Simpson', 'female.png', '#7c68fd', '#f1f1f1');
                    var maggie = member(400, 350, 'Manager', 'Maggie Simpson', 'female.png', '#feb563');
                    var lenny = member(190, 350, 'Manager', 'Lenny Leonard', 'male.png', '#feb563');
                    var carl = member(190, 500, 'Manager', 'Carl Carlson', 'male.png', '#feb563');
        */
        
        /*link(bart, marge, [{x: 385, y: 180}]);
                    link(bart, homer, [{x: 385, y: 180}, {x: 175, y: 180}]);
                    link(bart, lisa, [{x: 385, y: 180}, {x: 585, y: 180}]);
                    link(homer, lenny, [{x: 175, y: 380}]);
                    link(homer, carl, [{x: 175, y: 530}]);
                    link(marge, maggie, [{x: 385, y: 380}]);
*/
    }

    public String cargarPagina() {
        listaRol = rolService.buscarRol(new Rol());
        log.debug("METODO : RolController.cargarPagina");

        return "rolLista";
    }

    public void setRolService(RolService rolService) {
        this.rolService = rolService;
    }

    public List<Rol> getListaRol() {
        if (this.listaRol == null) {
            this.listaRol = new ArrayList<>();
        }
        return listaRol;
    }

    public void setListaRol(List<Rol> listaRol) {
        this.listaRol = listaRol;
    }

    public String getGraficaUsuarios() {
        return graficaUsuarios;
    }

    public void setGraficaUsuarios(String graficaUsuarios) {
        this.graficaUsuarios = graficaUsuarios;
    }
    
    
}
