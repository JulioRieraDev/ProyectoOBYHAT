package com.obyhat.principal;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.obyhat.controlador.BotonesObras;
import com.obyhat.modelo.dao.AsignarDAO;
import com.obyhat.modelo.dao.CategoriaDAO;
import com.obyhat.modelo.dao.ObraDAO;
import com.obyhat.modelo.dao.UsuarioDAO;
import com.obyhat.modelo.dto.AsignarDTO;
import com.obyhat.modelo.dto.CategoriaDTO;
import com.obyhat.modelo.dto.ObrasDTO;
import com.obyhat.modelo.dto.UsuarioDTO;
import com.obyhat.vista.paneles.PanelObras;
import com.obyhat.vista.principal.VistaPrincipal;
import java.util.List;

public class Pruebas {

	/*public static void main(String[] args) {
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		} catch(Exception e) {
			  
			System.out.println("Error al cargar el LAF: " + e);
		}*/
		
		/*// Instancias de las clases.
		ModeloObras modeloObras = new ModeloObras();
		PanelObras panelObras = new PanelObras();
		ControladorObras cObras = new ControladorObras(panelObras,modeloObras);
		
		
		
		// Frame para probar el panel individualmente.
		JFrame frame  = new JFrame();
        frame.setSize(1200, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panelObras);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);*/
		
		//VistaPrincipal vistaPrincipal = new VistaPrincipal();
		
		
		/* --------------------------------------------------------------------------
		 * PRUEBAS DEL PANEL OBRAS
		 * --------------------------------------------------------------------------
		 */
		
		//ObraDAO obraDAO = new ObraDAO();
		
		//ObrasDTO buscarObra = obraDAO.Consultar("La hojarasca");
		//System.out.println(buscarObra.getIdObra()+"\n"+buscarObra.getNombreObra());
		
		//obraDAO.Ingresar(new ObrasDTO("Obra 21","Fran","123456","Av. pOMPEYA"));
		
		//obraDAO.Eliminar(11);
		
		//obraDAO.Actualizar(new ObrasDTO(15,"Obra 22","Josue Martinez","454882","Av. Davalillo"));
		
		/*List<ObrasDTO> obras = obraDAO.ConsultarTodos();
                   
              for (int i = 0; i < obras.size(); i++) {
                  System.out.println(obras.get(i).getNombreObra());
              }*/
		
		/* --------------------------------------------------------------------------
		 * PRUEBAS DEL PANEL CATEGORIAS
		 * --------------------------------------------------------------------------
		 */
		
		//CategoriaDAO categoriaDAO = new CategoriaDAO();
		
			/*CategoriaDTO consultarCat = categoriaDAO.Consultar("Vidrios");
			//System.out.println(consultarCat.getIdCategoria()+" - "+consultarCat.getNombreCategoria()+" - "+consultarCat.);
			JOptionPane.showMessageDialog(null, consultarCat.getIdCategoria()+"\n"
											   +consultarCat.getNombreCategoria()+"\n"
											   +consultarCat.getDesCategoria(), null, JOptionPane.INFORMATION_MESSAGE);*/
				
			//categoriaDAO.Ingresar(new CategoriaDTO("Piedras","-OPCIONAL-"));
			
			//categoriaDAO.Eliminar("Piedras");
				
			//categoriaDAO.Actualizar(new CategoriaDTO(8,"Vidrio"," Todo referente a vidrios"));
			
			/*List<CategoriaDTO> categorias = categoriaDAO.ConsultarTodos();
		                   
		    for (int i = 0; i < categorias.size(); i++) {
		    	
		    	System.out.println(categorias.get(i).getIdCategoria()+" - "+categorias.get(i).getNombreCategoria()+" - "
		    					  +categorias.get(i).getDesCategoria());
		    }*/
		
		
		/* --------------------------------------------------------------------------
		 * PRUEBAS DEL PANEL USUARIOS
		 * --------------------------------------------------------------------------
		 */
		
			//UsuarioDAO usuarioDAO = new UsuarioDAO();
		
			/*CategoriaDTO consultarCat = categoriaDAO.Consultar("Vidrios");
			//System.out.println(consultarCat.getIdCategoria()+" - "+consultarCat.getNombreCategoria()+" - "+consultarCat.);
			JOptionPane.showMessageDialog(null, consultarCat.getIdCategoria()+"\n"
											   +consultarCat.getNombreCategoria()+"\n"
											   +consultarCat.getDesCategoria(), null, JOptionPane.INFORMATION_MESSAGE);*/
				
			//usuarioDAO.Ingresar(new UsuarioDTO("Gordo","Gordo"));
			
			//usuarioDAO.Eliminar("Gordo");
				
			//usuarioDAO.Actualizar(new UsuarioDTO(3,"Zophia","Zophia"));
			
			/*List<UsuarioDTO> usuarios = usuarioDAO.ConsultarTodos();
		                   
		    for (int i = 0; i < usuarios.size(); i++) {
		    	
		    	System.out.println(usuarios.get(i).getIdUsuario()+" - "+usuarios.get(i).getNombre()+" - "
		    					  +usuarios.get(i).getContrasena());
		    }*/
		
			/*List<UsuarioDTO> tUsuarios = usuarioDAO.obtenerTUsuarios();
	        
	        for (int i = 0; i < tUsuarios.size(); i++) {
	            System.out.println(tUsuarios.get(i).getIdTipoUsuario()+" - "+tUsuarios.get(i).getTipo_de_Usuario());
	        }*/
		
		/* --------------------------------------------------------------------------
		 * PRUEBAS DEL PANEL ASIGNAR
		 * --------------------------------------------------------------------------
		 */
		
		//AsignarDAO asignar = new AsignarDAO();
		
		/*AsignarDTO consultarAsig = asignar.Consultar(1);
			//System.out.println(consultarCat.getIdCategoria()+" - "+consultarCat.getNombreCategoria()+" - "+consultarCat.);
			JOptionPane.showMessageDialog(null, consultarAsig.getFechaAsignacion()+"\n"
											   +consultarAsig.getCantidadSeleccionada(),
											   null, JOptionPane.INFORMATION_MESSAGE);*/
				
			//asignar.Ingresar(new AsignarDTO("2017-4-27",7));
			
			//usuarioDAO.Eliminar("Gordo");
				
			//usuarioDAO.Actualizar(new UsuarioDTO(3,"Zophia","Zophia"));
			
			/*List<AsignarDTO> asignaciones = asignar.ConsultarTodos();
		                   
		    for (int i = 0; i < asignaciones.size(); i++) {
		    	
		    	System.out.println(asignaciones.get(i).getFechaAsignacion()+" - "+asignaciones.get(i).getCantidadSeleccionada());
		    }*/
		
		
	/*}*/

}
