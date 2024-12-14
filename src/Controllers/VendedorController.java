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
        throw new UnsupportedOperationException("Not supported yet."); 
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
