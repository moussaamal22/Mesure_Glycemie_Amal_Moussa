package com.example.moussa_amal_mesure_glycemie.controller;
import com.example.moussa_amal_mesure_glycemie.model.Patient;
public class Controller {
    private static Patient patient;
    private static Controller instance = null; // instance sauvegardée
    public Controller() {super();}  //on dirait constructeur par défaut
    public static final Controller getInstance()
    {
        if(instance==null)
            instance=new Controller();
        return Controller.instance;
    }
    //Flèche "UserAtion" View-->Controller-->Model
    public void createPatient(int age, float valeurMesuree, boolean oui)
    {
        //Flèche "Update" Controller-->Model
        patient = new Patient(age,valeurMesuree,oui);
    }
    //Flèche "Notify" Conttoller-->View
    public String getResult()
    {
        //Flèche "Notify" Model-->Conttoller
        return patient.getResult();
    }
}
