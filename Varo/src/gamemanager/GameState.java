package gamemanager;

public enum  GameState {

    WAITING("Wait")
    ,SCHUTZ("Save")
    ,FIGHT("Fight")
    ,STOPING("End");

    private String name;

    GameState(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
