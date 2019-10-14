package JFrame;

import GetSetters.ConexaoOrdenacao;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private static final long serialVersionUID = 2192699314198659323L;
    private List<ConexaoOrdenacao> lista;
    private String[] colunas = { "IdOrdenado" };

    public TableModel(List<ConexaoOrdenacao> lista) {
            this.lista = lista;
    }

    @Override
    public int getRowCount() {
            return lista.size();
    }

    @Override
    public int getColumnCount() {
            return colunas.length;
    }

    @Override
    public String getColumnName(int Indice_Da_Coluna) {
            return colunas[Indice_Da_Coluna];
    }

    @Override
    public Class<?> getColumnClass(int Indice_Da_Coluna) {
            return String.class;
    }

    @Override
    public void setValueAt(Object aValue, int Indice_Da_Linha, int Indice_Da_Coluna) {
            ConexaoOrdenacao get_and_set = lista.get(Indice_Da_Linha);

            switch (Indice_Da_Coluna) {
            /*
             * case 0: get_and_set.setId(Long.parseLong(aValue.toString()));
             */
            case 0:
                    get_and_set.setIdOrdenado(Integer.parseInt(aValue.toString()));
            default:
                    System.err.println("Índice da coluna é inválida");
            }
            fireTableCellUpdated(Indice_Da_Linha, Indice_Da_Coluna);
    }

    @Override
    public Object getValueAt(int Indice_Da_Linha, int Indice_Da_Coluna) {
            ConexaoOrdenacao get_and_set_Select = lista.get(Indice_Da_Linha);
            Object valueObject = null;

            switch (Indice_Da_Coluna) {

              case 0: 
                      valueObject = get_and_set_Select.getId();
             break;
            case 1:
                    valueObject = get_and_set_Select.getIdOrdenado();
                    break;
            default:
            }
            return valueObject;
    }

    @Override
    public boolean isCellEditable(int Indice_Da_Linha, int Indice_Da_Coluna) {
            return false;
    }

    public Object getSelecionado(int Indice_Da_Linha) {
            return lista.get(Indice_Da_Linha);
    }

    public void refreshData(List<ConexaoOrdenacao> atualizada) {
            this.lista = atualizada;
            fireTableDataChanged();
    }

    public void limpar() {
            lista.clear();
            fireTableDataChanged();
    }

    public boolean isEmpty() {
            return lista.isEmpty();
    }
}
