package Pokemon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;



/**
 *
 * @author jorgecisneros
 */
public class VentanaPokedex extends javax.swing.JFrame {

    private BufferedImage buffer;
    private Image imagenPokemons;
    private int contador = 0;
    private int tiempoDeVida = 0;
    private boolean automatico = false;
    private int ancho = 250, alto = 170;
    
    // conectamos a la base de datos

    private Statement estado;
    private ResultSet resultadoConsulta;
    private Connection conexion;
   
    ////////////////////////////////////////
    
    //hashmap para almacenar el resultado de la consulta
    HashMap <String,Pokemon> listaPokemons = new HashMap();
     HashMap <String,Pokemon> evolucionaDe = new HashMap();
    
    /**
     * Creates new form VentanaPokedex
     */
    private void dibujaElPokemonQueEstaEnLaPosicion (int posicion){
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        //borro lo que hubiera
        g2.setColor(Color.white);
        g2.fillRect(0, 0, ancho, alto);
        g2.drawImage(imagenPokemons,
                40,
                0,
                ancho-50,
                alto,
                96*columna,
                96*fila,
                96*columna + 96,
                96*fila + 96,
                null);
        repaint();
        escribeDatos();
         
       
        
    }
    
    private void modoAutomatico(){
   if(automatico){
    tiempoDeVida++;
    if(tiempoDeVida==1){
    contador++;
    }
    }
    }
    
    private void escribeDatos(){
        Pokemon p = listaPokemons.get(String.valueOf(contador+1));
        
        
        if (p != null){
            jLabel1.setText("Name:" + p.nombre);
             jLabel3.setText(p.height);
              jLabel4.setText(p.weight);
              jLabel8.setText("Specie: "+p.species);
              jLabel9.setText("Habitat: "+p.habitat);
              jLabel15.setText("Capture range: "+p.rango);
              
        }
        else {
            jLabel1.setText("Name: Unknown");
            jLabel3.setText("¿?");
            jLabel4.setText("¿?");
             jLabel8.setText("Specie: Unknown");
             jLabel9.setText("Habitat: Unknown");
             jLabel15.setText("Capture range: Unknown");
        }
        
        Pokemon q = listaPokemons.get(String.valueOf(p.EvolucionDe));
        if (q != null){
           
              jLabel17.setText("Evolves from: "+q.nombre);
        }else{
                jLabel17.setText("Evolves from: ----");
        }
        
    }
        
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0,ancho,alto, null);
    }
    
    public VentanaPokedex() {
        initComponents();
        try {
            imagenPokemons = ImageIO.read(getClass().getResource("black-white.png"));
        } catch (IOException ex) {
            Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        buffer =(BufferedImage) jPanel1.createImage(ancho,alto);
        Graphics2D g2 = buffer.createGraphics();
        
        
        
        
        //conexion a la base de datos//////////////////
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/test","root","");
            estado = conexion.createStatement();
            resultadoConsulta = estado.executeQuery("Select * from pokemon");
            //cargo el resultado de la query en mi hashmap
            while (resultadoConsulta.next()){
                Pokemon p = new Pokemon();
                p.nombre = resultadoConsulta.getString(2);
                p.generation_id = resultadoConsulta.getInt(5);
                p.EvolucionDe = resultadoConsulta.getInt(7);
                p.species = resultadoConsulta.getString(12);
                 p.height = resultadoConsulta.getString(10);
                 p.weight = resultadoConsulta.getString(11);
                 p.habitat = resultadoConsulta.getString(15);
                 p.rango = resultadoConsulta.getString(17);
               
                
                listaPokemons.put(resultadoConsulta.getString(1), p);
            }
        }
        catch (Exception e){
        }
        //////////////////////////////////////////////
         
        dibujaElPokemonQueEstaEnLaPosicion(0);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 162, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        jLabel3.setLabelFor(jLabel2);
        jLabel3.setText("11");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(535, 228, 50, 20));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        jLabel4.setLabelFor(jLabel2);
        jLabel4.setText("11");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(635, 228, 50, 20));

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 466, 90, 40));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("<--");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(235, 422, 40, 50));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("-->");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 422, 40, 50));

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setLabelFor(jLabel2);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 175, 160, 30));

        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel7MousePressed(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 400, 40, 40));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(587, 466, 90, 40));

        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel10MousePressed(evt);
            }
        });
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 406, 30, 30));

        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 406, 40, 30));

        jLabel12.setText("-10        +10");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 80, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Back");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(656, 438, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("End");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel14MousePressed(evt);
            }
        });
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(633, 376, 50, -1));

        jLabel15.setFont(new java.awt.Font("Arial Narrow", 1, 13)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 458, 120, 50));

        jLabel17.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 220, 30));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 0, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pokemon/pokedex.gif"))); // NOI18N
        jLabel2.setLabelFor(this);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, -4, 770, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed
        contador--;
        if (contador < 0) {contador = 0;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);
        
        
    }//GEN-LAST:event_jLabel5MousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
       contador++;
        if (contador > 649) {contador = 0;}
        dibujaElPokemonQueEstaEnLaPosicion(contador);
        

    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MousePressed
        contador = 0;
       dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jLabel7MousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
      if(contador > 10){
        contador = contador - 10;
      }else{contador=0;}
       dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel10MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MousePressed
         if(contador <639){
        contador = contador + 10;
      }else{contador=648;}
       dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jLabel10MousePressed

    private void jLabel14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MousePressed
        contador = 648;
       dibujaElPokemonQueEstaEnLaPosicion(contador);
    }//GEN-LAST:event_jLabel14MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPokedex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
