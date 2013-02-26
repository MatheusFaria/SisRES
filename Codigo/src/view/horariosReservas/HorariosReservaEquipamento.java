/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view.horariosReservas;

import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Equipamento;
import model.Patrimonio;
import model.ReservaEquipamentoProfessor;
import view.reservasEquipamentos.FazerReservaEquipamentoView;
import view.reservasEquipamentos.ReservaEquipamentoView;
import control.ManterResEquipamentoProfessor;
import exception.ClienteException;
import exception.PatrimonioException;
import exception.ReservaException;

/**
 * 
 * @author Parley
 */
public class HorariosReservaEquipamento extends HorariosReservaPatrimonio {

    Equipamento eq;
    ManterResEquipamentoProfessor instance;

    public HorariosReservaEquipamento(java.awt.Frame parent, boolean modal, String data, Equipamento eq) {
        super(parent, modal, data, eq);
        this.eq = eq;
    }

    protected Vector<String> fillDataVector(Object o, int index) {
        Vector<String> nomesTabela = new Vector<String>();
        if (o instanceof ReservaEquipamentoProfessor) {
            ReservaEquipamentoProfessor r = (ReservaEquipamentoProfessor) o;
            if (this.eq != null && (r.getEquipamento().equals(this.eq))) {

                nomesTabela.add(String.valueOf(index));
                nomesTabela.add(r.getHora());
                nomesTabela.add(r.getProfessor().getNome());
                nomesTabela.add(r.getProfessor().getMatricula());
                nomesTabela.add(r.getEquipamento().getCodigo());
                nomesTabela.add(r.getEquipamento().getDescricao());
            }
        }

        return nomesTabela;

    }

    @Override protected DefaultTableModel fillTable(Patrimonio equip) {
        this.eq = (Equipamento) equip;
        DefaultTableModel table = new DefaultTableModel();
        instance = ManterResEquipamentoProfessor.getInstance();
        try {
            table.addColumn("");
            table.addColumn("Hora:");
            table.addColumn("Nome");
            table.addColumn("Matricula");
            table.addColumn("Codigo Eqpt.");
            table.addColumn("Descricao Eqpt.");

            this.mes = Integer.parseInt(this.data.substring(3, 5));

            Vector<ReservaEquipamentoProfessor> v = instance.getReservasMes(mes);
            if (v != null)
                for (int i = 0; i < v.size(); i++) {
                    table.addRow(fillDataVector(v.get(i), i));

                }

        } catch (SQLException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PatrimonioException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClienteException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReservaException ex) {
            Logger.getLogger(HorariosReservaPatrimonio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return table;

    }

    @Override protected void cancelarReservaAction(int index) {
        try {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Deseja mesmo excluir Reserva?\n" + instance.getReservasMes(mes).get(index).toString(), "Excluir",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                this.instance.excluir(instance.getReservasMes(mes).get(index));
                JOptionPane.showMessageDialog(this, "Reserva excluida com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE,
                        null);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void reservarAction() {
        try {
            ReservaEquipamentoView reserva = new FazerReservaEquipamentoView(new JFrame(), true, this.eq, this.data);
            reserva.setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (PatrimonioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ClienteException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        } catch (ReservaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    @Override protected void alterarAction(int index) {
        /**
         * try { index = Integer.parseInt((String)
         * this.reservasTable.getModel().getValueAt(index, 0));
         * ReservaEquipamentoView reserva = new
         * AlterarReservaEquipamentoView(new JFrame(), true, index, this.mes);
         * reserva.setVisible(true);
         * 
         * } catch (SQLException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); } catch
         * (PatrimonioException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); } catch
         * (ClienteException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); } catch
         * (ReservaException ex) { JOptionPane.showMessageDialog(this,
         * ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, null); }
         */
    }
}
