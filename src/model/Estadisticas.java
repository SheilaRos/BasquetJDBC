package model;

public class Estadisticas {
    private String position;
    private int maxCanastas;
    private int minCanastas;
    private Double avgCanastas;
    private int maxAsistencias;
    private int minAsistencias;
    private Double avgAsistencias;
    private int maxRebotes;
    private int minRebotes;
    private Double avgRebotes;

    public Estadisticas() {
    }

    public Estadisticas(String position, int maxCanastas, int minCanastas, Double avgCanastas, int maxAsistencias, int minAsistencias, Double avgAsistencias, int maxRebotes, int minRebotes, Double avgRebotes) {
        this.position = position;
        this.maxCanastas = maxCanastas;
        this.minCanastas = minCanastas;
        this.avgCanastas = avgCanastas;
        this.maxAsistencias = maxAsistencias;
        this.minAsistencias = minAsistencias;
        this.avgAsistencias = avgAsistencias;
        this.maxRebotes = maxRebotes;
        this.minRebotes = minRebotes;
        this.avgRebotes = avgRebotes;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getMaxCanastas() {
        return maxCanastas;
    }

    public void setMaxCanastas(int maxCanastas) {
        this.maxCanastas = maxCanastas;
    }

    public int getMinCanastas() {
        return minCanastas;
    }

    public void setMinCanastas(int minCanastas) {
        this.minCanastas = minCanastas;
    }

    public Double getAvgCanastas() {
        return avgCanastas;
    }

    public void setAvgCanastas(Double avgCanastas) {
        this.avgCanastas = avgCanastas;
    }

    public int getMaxAsistencias() {
        return maxAsistencias;
    }

    public void setMaxAsistencias(int maxAsistencias) {
        this.maxAsistencias = maxAsistencias;
    }

    public int getMinAsistencias() {
        return minAsistencias;
    }

    public void setMinAsistencias(int minAsistencias) {
        this.minAsistencias = minAsistencias;
    }

    public Double getAvgAsistencias() {
        return avgAsistencias;
    }

    public void setAvgAsistencias(Double avgAsistencias) {
        this.avgAsistencias = avgAsistencias;
    }

    public int getMaxRebotes() {
        return maxRebotes;
    }

    public void setMaxRebotes(int maxRebotes) {
        this.maxRebotes = maxRebotes;
    }

    public int getMinRebotes() {
        return minRebotes;
    }

    public void setMinRebotes(int minRebotes) {
        this.minRebotes = minRebotes;
    }

    public Double getAvgRebotes() {
        return avgRebotes;
    }

    public void setAvgRebotes(Double avgRebotes) {
        this.avgRebotes = avgRebotes;
    }
    
    @Override
    public String toString() {
        return "Estadisticas{" + "position=" + position + ", máximo canastas=" + maxCanastas + ", mínimo canastas=" + minCanastas + ", AVG canastas=" + avgCanastas + ", máximo asistencias=" + maxAsistencias + ", mínimo asistencias=" + minAsistencias + ", AVG asistencias=" + avgAsistencias + ", máximo rebotes=" + maxRebotes + ", mínimo rebotes=" + minRebotes + ", AVG rebotes=" + avgRebotes + '}';
    }
       
}
