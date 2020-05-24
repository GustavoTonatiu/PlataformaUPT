package com.example.plataformaupt.ViewModels;
import java.util.List;
public class PeticionNoticia {
    public String estado;
    public List<PeticionNoticia>detalle;
    public PeticionNoticia(){}

    public PeticionNoticia (String estado,List<PeticionNoticia>detalle){
        this.estado=estado;
        this.detalle=detalle;
    }
    public String getEstado(){return estado;}
    public void setEstado(String estado){this.estado=estado;}
    public List<PeticionNoticia>getDetalle(){return detalle;}
    public void setDetalle(List<PeticionNoticia>detalle){this.detalle=detalle;}

}
