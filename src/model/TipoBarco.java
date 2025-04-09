package model;

public enum  TipoBarco {
    LANCHA(1),
    MEDICO(2),
    PROVISION(3),
    MUNICION(3),
    GUERRA(4),
    PORTAVIONES(5);
    
    private final int largoBarco;

    TipoBarco(int largoBarco){
        this.largoBarco = largoBarco;
    }

    public int getLargoBarco(){
        return largoBarco;
    }

}
