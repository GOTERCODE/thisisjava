package thisisjava0422;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BoardExample3 {

	private Scanner scanner = new Scanner(System.in);
	private Connection conn;

	public BoardExample3() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "C##GREEN", "GREEN1234"

			);
		} catch (Exception e) {
			e.printStackTrace();
			exit();
		}
	}

	public void create() {
		System.out.println("*** create 메소드 실행");
		list();
	}

	public void read() {
		System.out.println("*** read 메소드 실행");
		list();
	}

	public void clear() {
		System.out.println("*** clear 메소드 실행");
		list();
	}

	public void exit() {
		System.out.println("*** exit 메소드 실행");
		list();
	}

	public void list() {
		System.out.println();
		System.out.println("[게시글 목록]");
		System.out.println("==============================================");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "writer", "date", "title");
		System.out.println("==============================================");
		try {
			String sql = "" +
		"SELECT bno, btitle, bcontent, bwriter, bdate " +
					"FROM boards " +
		"ORDER BY bno DESC";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setBno(rs.getInt("bno"));
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontent"));
				board.setBwriter(rs.getString("bwriter"));
				board.setBdate(rs.getDate("bdate"));
				System.out.printf("%-6s%-12s%-16s%-40s \n", board.getBno(), board.getBwriter(), board.getBdate(),
						board.getBtitle());
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			exit();
		}
		mainMenu();
	}

	private void mainMenu() {
		System.out.println();
		System.out.println("==============================================");
		System.out.println("메인 메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit");
		System.out.println("메뉴 선택");
		String menuNo = scanner.nextLine();
		System.out.println();

		switch (menuNo) {
		case "1":
			create();
		case "2":
			read();
		case "3":
			clear();
		case "4":
			exit();

		}

	}

	public static void main(String[] args) {
		BoardExample3 boardExample = new BoardExample3();
		boardExample.list();

	}

}
