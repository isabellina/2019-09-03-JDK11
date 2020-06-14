package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.food.model.Arco;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;

public class FoodDao {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<String> getPorzioniCalorie(int c){
		String sql = "select  distinct portion.`portion_display_name` as nome " + 
				"from portion " + 
				"where portion.`calories`<? "
				+ "order by portion.`portion_display_name` ASC " ;
			
		try {
			List<String> lista = new LinkedList<String>() ;
		
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			st.setInt(1, c);
			
			ResultSet res = st.executeQuery() ;
			
				while(res.next()) {
				lista.add(res.getString("nome"));
		}
					
	    conn.close();
	    return lista;
		}   
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
	    return null;
	

 }
	public List<Arco> getArchi(){
		String sql = "select p1.`portion_display_name` as n1, p2.`portion_display_name` as n2, count(p1.`food_code`) as cnt " + 
				"from portion as p1, portion as p2 " + 
				"where p1.`food_code` = p2.`food_code` and p1.`portion_display_name`!=p2.`portion_display_name` " + 
				"group by p1.`portion_display_name`, p2.`portion_display_name` ";
		
		try {
			List<Arco> lista = new LinkedList<Arco>() ;
		
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			
			
			ResultSet res = st.executeQuery() ;
			
				while(res.next()) {
				lista.add(new Arco(res.getString("n1"),res.getString("n2"), res.getInt("cnt")));
		}
					
	    conn.close();
	    return lista;
		}   
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
		
	    return null;
	}
}	
