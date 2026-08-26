package figurinhas.model;

public class Figurinha implements Comparable<Figurinha> {
    private String nomeSelecao;
    private int numeroFigura;
    private String descricao;
    private int quantidade;
    private boolean raridade;

    public Figurinha(String nomeSelecao, int numeroFigura, String descricao, int quantidade, boolean raridade) {
        this.descricao = descricao;
        this.numeroFigura = numeroFigura;
        this.nomeSelecao = nomeSelecao;
        this.quantidade = quantidade;
        this.raridade = raridade;
    }

    @Override
    public int compareTo(Figurinha segunda) {
        // Posiciona as figurinhas alfabeticamente pela seleção, desempatando pelo número.
        int comparacaoSelecao = this.nomeSelecao.compareToIgnoreCase(segunda.nomeSelecao);

        if (comparacaoSelecao != 0) {
            return comparacaoSelecao;
        }

        // desempata pelo número
        return Integer.compare(this.numeroFigura, segunda.numeroFigura);
    }

    @Override
    public String toString() {
        return "Selc:" + nomeSelecao +
                " |Núm:" + numeroFigura +
                " |Desc:" + descricao +
                " |Qtd:" + quantidade +
                " |Rara:" + raridade;
    }

    @Override
    public boolean equals(Object obj) {
    
        if (!(obj instanceof Figurinha)) {
            return false;
        }
        Figurinha figurinha = (Figurinha) obj;
        // se dois registros tiverem o mesmo número e a mesma seleção, são considerados iguais
        return (this.numeroFigura == figurinha.numeroFigura)
                && this.nomeSelecao.equalsIgnoreCase(figurinha.nomeSelecao);
    }

    @Override
    public int hashCode() {
        // sempre que equals() é sobrescrito, hashCode() também deveria ser
       
        return java.util.Objects.hash(numeroFigura, nomeSelecao.toLowerCase());
    }

    public String getNomeSelecao() {
        return nomeSelecao;
    }

    public void setNomeSelecao(String nomeSelecao) {
        this.nomeSelecao = nomeSelecao;
    }

    public int getNumeroFigura() {
        return numeroFigura;
    }

    public void setNumeroFigura(int numeroFigura) {
        this.numeroFigura = numeroFigura;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isRaridade() {
        return raridade;
    }

    public void setRaridade(boolean raridade) {
        this.raridade = raridade;
    }
}
