/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.damian.pluginempaquetamiento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "org.damian.pluginempaquetamiento.PaqueteActionListener"
)
@ActionRegistration(
        iconBase = "org/damian/pluginempaquetamiento/images.png",
        displayName = "#CTL_PaqueteActionListener"
)
@ActionReference(path = "Toolbars/File", position = 0)
@Messages("CTL_PaqueteActionListener=Paquete")
public final class PaqueteActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Se piden las credenciales para el javapackager:
            String extensionPaquete = JOptionPane.showInputDialog("Extensión del paquete:\nPor ejemplo deb");
            String outfile = JOptionPane.showInputDialog("Nombre que tendrá el paquete:");
            String srcdir = JOptionPane.showInputDialog("Directorio en el que se encuentra el programa a empaquetar");
            String srcfiles = JOptionPane.showInputDialog("Nombre del jar que se va a empaquetar:\nEjemplo: Ejemplo.jar");
            String appClass = JOptionPane.showInputDialog("Nombre del paquete:\nEjemplo paquete.Paquete");
            String name = JOptionPane.showInputDialog("Nombre para el paquete: ");
            String title = JOptionPane.showInputDialog("Titulo para el paquete: ");
            String[] cmd = {"javapackager", "-deploy", "-native", extensionPaquete,
                "-Bcategory=Education", "-outdir .", "-outfile", outfile,
                "-srcdir ../../../../../" + srcdir,
                "-srcfiles ../../../../../" + srcdir + "/dist/" + srcfiles,
                "-appclass", appClass, "-name", name, " -title", title};
            Runtime rt = Runtime.getRuntime();
            Process pr = rt.exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = input.readLine()) != null){
                System.out.println(line);
            }
            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " +exitVal);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        } catch (InterruptedException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
