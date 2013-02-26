package model;

import exception.ReservaException;

public class ReservaEquipamentoProfessor extends ReservaEquipamento {

    private Professor professor;

    // Mensagens
    private final String PROFESSOR_NULO = "O professor esta nulo.";

    public ReservaEquipamentoProfessor(String data, String hora, Equipamento equipamento, Professor professor)
            throws ReservaException {
        super(data, hora, equipamento);
        this.setProfessor(professor);
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) throws ReservaException {
        if (professor == null)
            throw new ReservaException(PROFESSOR_NULO);
        this.professor = professor;
    }

    public boolean equals(ReservaEquipamentoProfessor obj) {
        return (super.equals(obj) && this.getEquipamento().equals(obj.getEquipamento()));
    }

    @Override public String toString() {
        return "ReservaEquipamentoProfessor [professor=" + this.getEquipamento().toString() + ", toString()=" + super.toString()
                + "]";
    }

}
