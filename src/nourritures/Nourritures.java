public enum Nourritures{
    BISCUITS(3),
    CHOCOLATSCHAUD(5),
    GUIMAUVES(2),
    GRUAU(2),
    FRIANDISE(2);

    private Integer nutrition;

    Nourritures(Integer nutrition){
        this.nutrition = nutrition;
    }

    public Integer getNutrition(){
        return this.nutrition;
    }
}