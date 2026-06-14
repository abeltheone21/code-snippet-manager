package databaseOperations;
import dataBaseConnection.connectionEstablish;
import model.Snippet;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class dataQR {
   public void addSnippet(String title, String language, String code, String description) {

       Connection conn = connectionEstablish.getConnection();

       String sql = "INSERT INTO snippets (title, language, code, description,favorite) VALUES (?, ?, ?, ?, ?)";

       try {
           PreparedStatement pst = conn.prepareStatement(sql);

           pst.setString(1, title);
           pst.setString(2, language);
           pst.setString(3, code);
           pst.setString(4, description);
           pst.setInt(5, 0);

           pst.executeUpdate();

           System.out.println("Snippet added successfully");

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void updateFavorite(int id,boolean favorite) {
       Connection conn = connectionEstablish.getConnection();
       String sql = "UPDATE snippets SET favorite = ? WHERE id = ?";
       try{
           PreparedStatement pst=conn.prepareStatement(sql);
           pst.setInt(1,favorite?1:0);
           pst.setInt(2,id);
           pst.executeUpdate();
           System.out.println("favorite updated successfully");


       }
       catch(Exception e){
           e.printStackTrace();
       }
   }
   //this methode gets all the values form the database
   public List<Snippet> getallsnippets() {
       List<Snippet> snippets = new ArrayList<>();
       Connection conn = connectionEstablish.getConnection();
       String sql = "SELECT * FROM snippets";
       try {
           PreparedStatement pst = conn.prepareStatement(sql);
           ResultSet rs=pst.executeQuery();
           while(rs.next()){
               Snippet s= new Snippet(
                       rs.getInt("id"),
                       rs.getString("title"),
                       rs.getString("language"),
                       rs.getString("code"),
                       rs.getString("description"),
                       rs.getInt("favorite")==1

               );
               snippets.add(s);

           }
           return snippets;
       }
       catch (Exception e) {
          e.printStackTrace();
          return null;
       }


   }
    // Get recent snippets (last 10 added)
    public List<Snippet> getRecentSnippets() {
        List<Snippet> snippets = new ArrayList<>();
        Connection conn = connectionEstablish.getConnection();
        String sql = "SELECT * FROM snippets ORDER BY id DESC LIMIT 10";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Snippet s = new Snippet(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("language"),
                        rs.getString("code"),
                        rs.getString("description"),
                        rs.getInt("favorite") == 1
                );
                snippets.add(s);
            }
            return snippets;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
   public void updateSnippet(int id,String title,String language,String code,String description) {
       Connection conn = connectionEstablish.getConnection();
       String sql="UPDATE snippets SET title=?, language=?, code=?, description=? WHERE id=?";
       try{
           PreparedStatement pst=conn.prepareStatement(sql);
           pst.setString(1, title);
           pst.setString(2, language);
           pst.setString(3, code);
           pst.setString(4, description);
           pst.setInt(5, id);
           pst.executeUpdate();
           System.out.println("Snippet updated successfully");

       }
       catch (Exception e) {
           e.printStackTrace();

       }
   }
   public void deleteSnippet(int id) {
       Connection conn = connectionEstablish.getConnection();
       String sql="delete from snippets where id=?";
       try{
           PreparedStatement pst=conn.prepareStatement(sql);
           pst.setInt(1,id);
           pst.executeUpdate();
           System.out.println("Snippet deleted successfully");

       }
       catch (Exception e) {
           e.printStackTrace();

       }
   }
}
