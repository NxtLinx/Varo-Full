package mysql;

import de.nukez.main.Main;
import gamemanager.GameState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLManager {

    private static MySQL mySQL = Main.getPlugin().getMySQL();

    public static GameState getMySQlGame(){

        GameState gameState = GameState.WAITING;
        if (gamestateIsAktiv()) {
                switch (getGame()){
                    case "Wait":
                       gameState = GameState.WAITING;
                        break;
                    case"Save":
                        gameState = GameState.SCHUTZ;
                        break;
                    case "Fight":
                        gameState = GameState.FIGHT;
                        break;
                    case "End":
                        gameState = GameState.STOPING;
                        break;
                    default:
                        gameState = GameState.WAITING;
                        break;
                }
        }
        return gameState;
    }


    public static void setNextState(){
        if (gamestateIsAktiv()) {
            switch (getGame()){
                case "Wait":
                   setGameState(GameState.SCHUTZ);
                    break;
                case"Save":
                    setGameState(GameState.FIGHT);
                    break;
                case "Fight":
                    setGameState(GameState.STOPING);
                    break;
                case "End":
                    setGameState(GameState.WAITING);
                    break;
                default:
                    setGameState(GameState.WAITING);
                    break;
            }
        }
    }

    private static boolean gamestateIsAktiv(){
        try {
            ResultSet rs = mySQL.query("SELECT * FROM gamemanager");
            if (rs.next()){
                return rs.getString("gamestate") != null;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String getGame(){
            try {
                ResultSet rs = mySQL.query("SELECT gamestate FROM gamemanager");
                if ((!rs.next()) || (rs.getString("gamestate")) == null);
                return rs.getString("gamestate");
            } catch (SQLException ex){
                ex.printStackTrace();
            }
            return "";
    }

    public static void setGameState(GameState gameState){
        if (gamestateIsAktiv()) {
            mySQL.update("UPDATE anmelden SET password = '"+gameState.getName()+"';");
        }
    }


}

