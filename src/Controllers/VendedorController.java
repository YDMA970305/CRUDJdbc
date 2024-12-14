/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

/**
 *
 * @author pc
 */

import Configuracion.ConexionLocal;
import Interface.IGestorDatos;
import Model.Vendedormodel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class VendedorController implements  IGestorDatos<Vendedormodel>{
    
    private Connection cnn;
    private final ConexionLocal connNew=new ConexionLocal();
    
    
    @Override
    public void creación(Vendedormodel objeto) {
        try{
            connNew.conectar();
        String sql="INSERT INTO vendedor (dni_vendedor,nombre_vendedor,direccion_vendedor,email_vendedor)"+"VALUES (?,?,?,?)";
        PreparedStatement st= connNew.getConexion().prepareStatement(sql);
        st.setInt(1, Integer.parseInt(objeto.getDni_vendedor()));
        st.setString(2, objeto.getNombre_vendedor());
        st.setString(3, objeto.getDireccion_vendedor());
        st.setString(4, objeto.getEmail_vendedor());
        st.execute();
        JOptionPane.showMessageDialog(null,"Se ha registrado un nuevo vendedor");
        }catch(Exception e){
        JOptionPane.showMessageDialog(null,"no se puede guardar registro");
        e.printStackTrace();
        }
    }

    @Override
    public Vendedormodel lectura(int id) {
       String sql="SELECT dni_vendedor,nombre_vendedor,direccion_vendedor,email_vendedor FROM vendedor WHERE dni_vendedor='"+id+"'";
       
       Vendedormodel VendedorTraido=new Vendedormodel();
       try{
           connNew.conectar();
           PreparedStatement realizarConsulta =connNew.getConexion().prepareStatement(sql);
           ResultSet resultado=realizarConsulta.executeQuery();
           if(resultado.next()){
               VendedorTraido.setDni_vendedor(resultado.getString("dni_vendedor"));
               VendedorTraido.setNombre_vendedor(resultado.getString("nombre_vendedor"));
               VendedorTraido.setDireccion_vendedor(resultado.getString("direccion_vendedor"));
               VendedorTraido.setEmail_vendedor(resultado.getString("email_vendedor"));          
           }
           else {
               VendedorTraido=new Vendedormodel();
               JOptionPane.showInternalMessageDialog(null, "No se encontraron Datos");
           }
       }catch(Exception e){
       JOptionPane.showMessageDialog(null, "No se encontraron registros.","Error al recuperar datos",JOptionPane.ERROR_MESSAGE);
           System.out.println("Error en la clase:"+this.getClass().getName());
       }
       return VendedorTraido;
    }

    @Override
    public void actualizar(Vendedormodel objeto, int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void eliminar(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
