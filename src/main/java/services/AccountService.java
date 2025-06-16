package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.accounts;
import utils.Db;
public class AccountService {

	public List<accounts> select(int n) {
		List<accounts> list = new ArrayList<>();
		String sql = "SELECT * FROM accounts";
		//				String sql = "SELECT * FROM accounts";
		//				String sql = "SELECT * FROM accounts";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, n);
			ResultSet rs = pstmt.executeQuery();
			//
			while (rs.next()) {
				accounts a = new accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getByte("authority"));
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public void insert(accounts a) {
		String sql = "INSERT INTO accounts (name, mail, password, authority) VALUES (?, ?, ?, ?)";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getMail());
			pstmt.setString(3, a.getPassword());
			pstmt.setInt(4, a.getAuthority());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(accounts a) {
		String sql = "UPDATE accounts SET name = ?, mail = ?, password  = ? ";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getMail());
			pstmt.setString(3, a.getPassword());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(accounts a) {
		String sql = "DELETE FROM account WHERE id = ?";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, a.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<accounts> findById(int account_id) {

		String sql = "SELECT * FROM accounts WHERE id = ?";

		ArrayList<accounts> accounts = new ArrayList<>();

		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, account_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				accounts account = new accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getByte("authority"));
				accounts.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	public ArrayList<accounts> findByAccount(String name, String mail, byte authority) {

		StringBuilder sql =new StringBuilder ("SELECT * FROM accounts WHERE 1=1 ");
		ArrayList<Object> params = new ArrayList<>();
		ArrayList<accounts> accounts = new ArrayList<>();

			if (name != "") {
				
				sql.append(" AND name LIKE ?");
				
				params.add("%" + name +"%");

			}
			if (mail != "") {

				sql.append (" AND mail = ?");
				
				params.add(mail);
				
			}
			if (authority != 0) {

				sql.append(" AND authority = ?");
				
				byte hikizan = 1;
				authority -= hikizan;
				
				params.add(authority);
				
			
			}try (Connection conn = Db.open();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
				
				for(int i = 0 ; i < params.size(); i++) {
				
					Object param = params.get(i);
					
					if(param instanceof String) {
						pstmt.setString(i+1, (String)param);
					}else if(param instanceof Byte) {
						pstmt.setByte(i+1, (Byte)param);
					}
				}
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					accounts account = new accounts(
							rs.getInt("account_id"),
							rs.getString("name"),
							rs.getString("mail"),
							rs.getString("password"),
							rs.getByte("authority"));
					accounts.add(account);					
				}		
			}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sql);
		return accounts;
	}

	}
