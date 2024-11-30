package com.p3.inventarioactivos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioController {
    private static final String NOMBRE_ARCHIVO = "inventario_ti.csv";

    @FXML
    private TextField idField;
    @FXML
    private TextField tipoField;
    @FXML
    private TextField marcaField;
    @FXML
    private TextField modeloField;
    @FXML
    private TextField serialField;
    @FXML
    private TextField responsableField;
    @FXML
    private ListView<String> listViewActivos;
    @FXML
    private TextField nombreActivoField; // Campo de texto para ingresar el nombre del activo
    @FXML
    private TextField descripcionActivoField;


    @FXML
    private void agregarActivo() {
        String id = idField.getText();
        String tipo = tipoField.getText();
        String marca = marcaField.getText();
        String modelo = modeloField.getText();
        String serial = serialField.getText();
        String responsable = responsableField.getText();

        if (id.isEmpty() || tipo.isEmpty() || marca.isEmpty() || modelo.isEmpty() || serial.isEmpty() || responsable.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        String activo = id + ";" + tipo + ";" + marca + ";" + modelo + ";" + serial + ";" + responsable;

        try (FileWriter fw = new FileWriter(NOMBRE_ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(activo);

            limpiarCampos();
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo guardar el activo.", Alert.AlertType.ERROR);
        }
    }
    private ObservableList<Activo> listaActivos = FXCollections.observableArrayList();

    @FXML
    private void consultarActivos() {
        File archivo = new File(NOMBRE_ARCHIVO);
        if (!archivo.exists()) {
            mostrarAlerta("Información", "No hay activos registrados.", Alert.AlertType.INFORMATION);
            return;
        }

        List<String> activos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                activos.add(linea.replace(";", " ; "));
            }
            listViewActivos.getItems().setAll(activos);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo leer el archivo.", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        idField.clear();
        tipoField.clear();
        marcaField.clear();
        modeloField.clear();
        serialField.clear();
        responsableField.clear();
    }

    @FXML
    private void guardarEnArchivo(ActionEvent actionEvent) {
        String rutaArchivo = "activos.txt";
        ObservableList<String> activos = listViewActivos.getItems();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (String activo : activos) {
                writer.write(activo.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar los activos en el archivo.");
        }

    }
    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }

}
