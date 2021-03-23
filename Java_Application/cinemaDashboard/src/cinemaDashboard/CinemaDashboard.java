package cinemaDashboard;
import java.sql.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CinemaDashboard {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {

	
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "cinema";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		
		
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			System.out.println("Connected to DB");
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to connect to DB");
		}
		
		
		
		//-----LETS BEGIN-----
		
		//-----Room Capacities---
		int capDiamond = 250;
		int capGalaxy = 120;
		int capMoonlight = 70;
		int capPilio = 150;
		int capRuby= 210;
		int capSapphire = 200;
		
		int checkExit = 0;
		
		do {
		System.out.println("\n\n    - Cinema Dashboard -");	
		System.out.println("\n\nPlese select Input:");
		System.out.println("\n\n1. Input data about Movies/Customers");
		System.out.println("\n2. See what's playing now per theatre");
		System.out.println("\n3. See what's playing in full rooms");
		System.out.println("\n4. See available viewings per query");
		System.out.println("\n5. Make a reservation");
		System.out.println("\n6. Change reservation status");
		System.out.println("\n7. Delete movies");
		System.out.println("\n8. Exit");
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("\n Select: ");
		int selection = input.nextInt();
		System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");

		while ( !(	selection == 1 || 
					selection == 2 || 
					selection == 3 || 
					selection == 4 || 
					selection == 5 || 
					selection == 6 || 
					selection == 7 || 
					selection == 8 ) ){
			
			System.out.println("\n\n Invalid Input");
			System.out.println("\n Select: ");
			selection = input.nextInt();
			
			System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");
		}

		
		if(selection == 1) {
			System.out.println("\n Select where to input data: ");
			
			
			int input1 = 0;
			
			do {
				System.out.println("\n\n1. Input data about Movies");
				System.out.println("\n2. Input data about Customers");
				input1 = input.nextInt();
			} while (!(input1 == 1 || input1 == 2));
					
					if(input1 == 1) {
						//--INPUT DATA TO MOVIES TABLE
						try {
							
							Class.forName(driver).newInstance();
							
							System.out.println("\nInserting data about Movies: ");
							
							
							System.out.println("\n Movie ID (integer): ");
							int inMovieID = input.nextInt();
							
							input.nextLine();
							System.out.println("\n \n Movie Title (Text): ");
							String inMovieTitle = input.nextLine();
							
							System.out.println("\n Movie Description (Text): ");
							String inMovieDescription = input.nextLine();

							System.out.println("\n Movie Director (Text): ");
							String inMovieDirector = input.nextLine();

							System.out.println("\n Movie Genre (Text): ");
							String inMovieGenre = input.nextLine();

							System.out.println("\n Movie Actors (Text): ");
							String inMovieActros = input.nextLine();
							
							System.out.println("\n Movie Duration (integer): ");
							int inMovieDuration = input.nextInt();
							
							
							
							String sql = "insert into movie" + "(id, title, description, director, genre, actors, duration)" + "VALUES (?,?,?,?,?,?,?)";
							
							PreparedStatement preparedStatement = conn.prepareStatement(sql);
							
							preparedStatement.setInt(1, inMovieID);
							preparedStatement.setString(2, inMovieTitle);
							preparedStatement.setString(3, inMovieDescription);
							preparedStatement.setString(4, inMovieDirector);
							preparedStatement.setString(5, inMovieGenre);
							preparedStatement.setString(6, inMovieActros);
							preparedStatement.setInt(7, inMovieDuration);

							preparedStatement.executeUpdate();
							
							System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");

					
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						
					}
					if(input1 == 2) {
						//--INPUT DATA TO CUSTOMERS TABLE
						try {
							Class.forName(driver).newInstance();
							
							System.out.println("\nInserting data about Customers: ");
							
							System.out.println("\n Customer ID (Integer): ");
							int inCustomerID = input.nextInt();
							
							System.out.println("\n Customer's Name (Text): ");
							String inCustomerName = input.next();
							
							System.out.println("\n Customer's Surname (Text): ");
							String inCustomerSurname = input.next();
							
							System.out.println("\n Customer's E-Mail (Text): ");
							String inCustomerEmail = input.next();
							
							System.out.println("\n Customer's Mobile Phone (Text): ");
							String inCustomerMobile = input.next();
							
							System.out.println("\n Customer's Number of Tickets to be Reserved/Purchased (Integer): ");
							int inCustomerNumTickets = input.nextInt();
							
							
							
							
							String sql = "insert into customer" + "(id,name,surname,email,mobilephone,numtickets)" + "VALUES(?,?,?,?,?,?)";
							
							PreparedStatement preparedStatement = conn.prepareStatement(sql);
							
							preparedStatement.setInt(1, inCustomerID);
							preparedStatement.setString(2, inCustomerName);
							preparedStatement.setString(3, inCustomerSurname);
							preparedStatement.setString(4, inCustomerEmail);
							preparedStatement.setString(5, inCustomerMobile);
							preparedStatement.setInt(6, inCustomerNumTickets);

							preparedStatement.executeUpdate();
							
							System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");

						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
						
						
					}
					
					
		}
		
		if(selection == 2) {
			//See what's playing now per theater
			
			
			try {
				System.out.println("\n See what's playing.");
				System.out.println("\n Select Cinema Theater: (Village_Athens | Village_Volos)");
				String theater = input.next();
				
				
				
					
					//Get the ID of the Theater
				String sql0 = "SELECT id FROM cinema WHERE cinemaname = ?";
				PreparedStatement preparedStatement0 = conn.prepareStatement(sql0);
				preparedStatement0.setString(1, theater);
				ResultSet rs0 = preparedStatement0.executeQuery();
				rs0.next();
				int cinemaID = rs0.getInt("id");
				
				
				//Get the Room Names for the selected Theater
				String sql1 = "SELECT roomname FROM room WHERE cinema_id = ?";
				PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
				preparedStatement1.setInt(1, cinemaID);
				ResultSet rs1 = preparedStatement1.executeQuery();

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:00");
					LocalDateTime now = LocalDateTime.now();
					System.out.println(dtf.format(now)); //2016-11-16 12:08:00
					String timeNOW = dtf.format(now);
					
					//forEach room see what's playing
				
				while (rs1.next()) {
					
					String roomName = rs1.getString("roomname");
					System.out.format("\n See what's playing now on Room: %s \n", roomName);
					
					
					String sql2 = "SELECT movieviewing FROM cinema.viewing WHERE roomviewing = ? AND timeviewing = ?";
					PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
					preparedStatement2.setString(1, roomName);
					preparedStatement2.setString(2, timeNOW);
					ResultSet rs2 = preparedStatement2.executeQuery();
					while(rs2.next()) {
					String pNowInRoom = rs2.getString("movieviewing");
					System.out.format("\n %s", pNowInRoom);
					}			
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
			
		}
		
		if(selection == 3) {
			//See what's playing in full rooms
			
			
			
			try {
				System.out.println("\n Select Cinema Theater: (Village_Athens | Village_Volos)");
				String theaterExamined = input.next();
				
				
				
					
				//first, get the ID of the Theater
				String sql0 = "SELECT id FROM cinema WHERE cinemaname = ?";
				PreparedStatement preparedStatement0 = conn.prepareStatement(sql0);
				preparedStatement0.setString(1, theaterExamined);
				ResultSet rs0 = preparedStatement0.executeQuery();
				rs0.next();
				int cinemaID = rs0.getInt("id");
				
				
				
				// Then, get the room name(s) based on Cinema_ID
				String sql1 = "SELECT roomname FROM room WHERE numofseats = 0 AND cinema_id = ?";
				PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
				preparedStatement1.setInt(1, cinemaID);
				ResultSet rs1 = preparedStatement1.executeQuery();
				
				//for each roonName see what's playing
				while (rs1.next()) {
					
					String roomNametoExamine = rs1.getString("roomname");
					String sql2 = "SELECT movieviewing FROM viewing WHERE roomviewing = ? ";
					PreparedStatement preparedStatement2 = conn.prepareStatement(sql2);
					preparedStatement2.setString(1, roomNametoExamine);
					ResultSet rs2 = preparedStatement2.executeQuery();
					rs2.next();
					String movieReturned = rs2.getString("movieviewing");
					
					System.out.format("\n Full room: %s , Playing: %s",roomNametoExamine,movieReturned);
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		if(selection == 4) {
			//See available viewings 
			
			
			try {
				System.out.println("\n Retrieving Viewings: ");
				
				System.out.println("\n Cinema Location: (Village_Athens | Village_Volos)");
				String qCinemaName = input.next();
				
				String sql0 = "SELECT roomname FROM room WHERE ofcinema = ? AND numofseats > 0";
				PreparedStatement psql0 = conn.prepareStatement(sql0);
				psql0.setString(1, qCinemaName);
				ResultSet rs0 = psql0.executeQuery();
				String roonNamers0;
				
						
				
				System.out.println("\n Movie Duration: ");
				int qMovieDuration = input.nextInt();
				
				System.out.println("\n Movie Title: ");
				String qMovieTitle = input.next();
				
				System.out.println("\n Movie Genre: ");
				String qMovieGenre = input.next();
				
				String sql1 = "SELECT id FROM movie WHERE title = ? AND genre = ? AND duration  = ?";
				PreparedStatement psql1 = conn.prepareStatement(sql1);
				psql1.setString(1, qMovieTitle);
				psql1.setString(2, qMovieGenre);
				psql1.setInt(3, qMovieDuration);
				ResultSet rs1 = psql1.executeQuery();
				rs1.next();
				int movieIDrs1 = rs1.getInt("id");
							

				
				System.out.println("\n Date : ");
				System.out.println("\n Please use the following format: yyyy-MM-dd");
				String qDate = input.next();
				
				System.out.println("\n Time : ");
				System.out.println("\n Please use the following format: HH:mm:00");
				String qTime = input.next();
				

				String qDateAndTimeIN = qDate + " " + qTime;
				
				
				while(rs0.next()) {	
				roonNamers0 = rs0.getString("roomname");
				
				String sql2 = "SELECT id, movieviewing FROM viewing WHERE movie_id = ? AND timeviewing = ? AND room_roomname = ?";
				PreparedStatement psql2 = conn.prepareStatement(sql2);
				psql2.setInt(1, movieIDrs1);
				psql2.setString(2, qDateAndTimeIN);
				psql2.setString(3, roonNamers0);
				ResultSet rs2 = psql2.executeQuery();
							
		
						if(rs2.next() == true){
						
						int foundViewingId = rs2.getInt("id");
						String foundMovieViewing = rs2.getString("movieviewing");
						
						System.out.format("\n Viewing ID: %d, Movie: %s on Room: %s at: %s",foundViewingId,foundMovieViewing,roonNamers0,qDateAndTimeIN);
						}else {
							System.out.println("\n Nothing else");
						}
				
				}
			
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(selection == 5) {
			//Make a reservation
			
			
		
			try {
				System.out.println("\n Select the id of the viewing you want to book tickets for:");

				String sqlViewings = "SELECT id, movieviewing, roomviewing, timeviewing, movie_id FROM viewing ORDER BY timeviewing ASC";
				PreparedStatement viewingsPStatement = conn.prepareStatement(sqlViewings);
				ResultSet resViewings = viewingsPStatement.executeQuery();
				
				while(resViewings.next()) {
					int vID = resViewings.getInt("id");
					String vMovieTitle = resViewings.getString("movieviewing");
					String vTimeViewing = resViewings.getString("timeviewing");
					String vRoomName = resViewings.getString("roomviewing");
					
					System.out.format("\n Viewing ID: %d, Movie: %s, on: %s at Room: %s ",vID,vMovieTitle,vTimeViewing,vRoomName);
				}
				
				
				
				System.out.println("\nInserting data about Reservations: ");
				
				System.out.println("\n Reservation ID (Integer): ");
				int inResID = input.nextInt();
				
				System.out.println("\n Customer's ID (Integer): ");
				int inResCustomerID = input.nextInt();
					
				System.out.println("\n Viewing's ID (Integer): ");
				int inResViewingFKID = input.nextInt();
				
				//System.out.println("\n Movie's ID (Integer): ");
				//int inResMovieID = input.nextInt();
				
				System.out.println("\n Amount of Tickets: ");
				int numTickets = input.nextInt();
				
				int price = numTickets * 7;
				System.out.format("\n Total Price: %d Euros ", price);
				
				
				
				
				System.out.println("\n Status (Pending|Completed): ");
				String inResStatus = input.next();
				
				
				//System.out.println("\n Customer's ID (Integer): ");
				//int inResCustomerFKID = input.nextInt();
				int inResCustomerFKID = inResCustomerID;
				
				String sql = "insert into reservation" + "(id,idcustomer,idmovie,status,viewing_id,customer_id)" + "VALUES (?,?,NULL,?,?,?)";
				
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				
				preparedStatement.setInt(1, inResID);
				preparedStatement.setInt(2, inResCustomerID);
				//preparedStatement.setInt(3, inResMovieID);
				preparedStatement.setString(3, inResStatus);
				preparedStatement.setInt(4, inResViewingFKID);
				preparedStatement.setInt(5, inResCustomerFKID);
				
				System.out.println(" \n All ok to proceed? (1)Yes (0)No");
				int proceed = input.nextInt();
				
				if (proceed == 1) {
					preparedStatement.executeUpdate();	
					
					
					//update seat numbers
					
					
					//find the corresponding room for the reservation
				String sqlSeats = "SELECT roomviewing FROM viewing WHERE id = ?";
				PreparedStatement psqlSeats = conn.prepareStatement(sqlSeats);
				psqlSeats.setInt(1, inResViewingFKID);
				ResultSet row = psqlSeats.executeQuery();
				row.next();
				String roomToUpdateCap = row.getString("roomviewing");
				
				
				
					//decrease the number of seats available said room
				String updateSeats = "UPDATE room SET numofseats = numofseats - ? WHERE roomname = ?";
				PreparedStatement pupdateSeats = conn.prepareStatement(updateSeats);
				pupdateSeats.setInt(1, numTickets);
				pupdateSeats.setString(2, roomToUpdateCap);
				pupdateSeats.executeUpdate();
				
					//update the customer's number of seats on "customer" table
				String customerSeatsNew = "UPDATE customer SET numtickets = ?";
				PreparedStatement pCSeatsNew = conn.prepareStatement(customerSeatsNew);
				pCSeatsNew.setInt(1, numTickets);
				pCSeatsNew.executeUpdate();
				
				
				}
				else {
					System.out.println("\n Reservation Aborted!");
				}
				
				

				System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}

					
		}
		
		if(selection == 6) {
			//Search for a reservation to change its status
			
			
			try {
				int purchase = 0;
				System.out.println("\n Updating Reservation Status: ");
				System.out.println("\n Reservation ID: ");
				int uResID = input.nextInt();
				
				
				do {
					
					System.out.println("\n Has the customer completed the purchase? ");
					System.out.println("\n Select: 1=YES 0=NO ");
					purchase = input.nextInt();
				} while (purchase == 0);
	
					
				String sql = "update reservation set status = 'Completed' where id = ?";
				
				PreparedStatement preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setInt(1, uResID);
				preparedStatement.executeUpdate();
				
				System.out.println("Reservation Updated!");
				System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		if(selection == 7) {
			//Delete movies
			
			try {
				System.out.println("\n Deleting Movies");
				System.out.println("\n WARNING WARNING WARNING");
				System.out.println("\n You are about to delete movies from the System. This action is not reversible!");
				System.out.println("\n Movie ID to be DELETED: ");
				int idtbd = input.nextInt();
				
				String sql = "DELETE FROM movie WHERE id = ?";
				
				PreparedStatement preparedStatement = conn.prepareStatement(sql); 
				preparedStatement.setInt(1, idtbd);

				preparedStatement.executeUpdate();
				
				System.out.println("\n Movie Deleted");
				System.out.println("\n\n----------------------------------------------------------------------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}

					
		}
		if(selection == 8) {
			//Exit
			System.out.println("TERMINATED");
			checkExit = 1;
			conn.close();			
		}
		
		
	
	}while (checkExit == 0);
		
		
	}//end of main
	
}//end of class
