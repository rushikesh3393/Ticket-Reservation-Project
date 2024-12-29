package ticketreservation.clientapp;

import java.util.*;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import ticketreservation.model.*;
import ticketreservation.service.*;

public class TicketReservationApp 
{
	static Logger logger = Logger.getLogger(TicketReservationApp.class);
	static {
		PropertyConfigurator.configure(
				"C:\\Users\\HP\\eclipse-workspace\\TicketReservationSystemForEvent\\src\\main\\resources\\Application.properties");
	}

	public static void main(String[] args) 
	{
		logger.debug("Main Method Started");
		UserService userService = new UserServiceImpl();
		CategoryService categoryService = new CategoryServiceImpl();
		EventService eventService = new EventServiceImpl();
		UserModel model = new UserModel();
		CategoryModel cmodel = new CategoryModel();
		EventModel emodel = new EventModel();
		List<EventModel> elist = new ArrayList<EventModel>();
		BookingModel bmodel=new BookingModel ();
		BookingService bookingService=new BookingServiceImpl();
		List<BookingModel> bklist=new ArrayList<BookingModel>();
		List<BookingModel> ubklist=new ArrayList<BookingModel>();
		List<BookingModel> reciptlist=new ArrayList<BookingModel>();

		String limit;
		Scanner xyz = new Scanner(System.in);

		System.out.println(
				"\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
		System.out.println(
				"==================================================================      WELCOME TO TICKET RESERVATION APP      ===================================================");
		System.out.println(
				"\n-------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

		try {
			
		
		do {
			System.out.println("==> If You Are Already Registered, Then You Can Login \n");
			System.out.println("1. Registration");
			System.out.println("2. Login");
			System.out.println("3. Exit");

			System.out.println("\nEnter Your Choice:");
			int choice = xyz.nextInt();

			switch (choice) 
			{
			case 1:
				System.out.println(
						"===========================================================     REGISTRATION FORM       =======================================================================");
				
				
				do {
					System.out.println("1. Register as Admin");
					System.out.println("2. Register as Customer");

					System.out.println("Enter your choice:");
					int Choice = xyz.nextInt();

					switch (Choice) {
					case 1:
						try {
						System.out.println("Enter Key to Register as Admin:");
						xyz.nextLine();
						String adminKey = xyz.nextLine();
						model.setKey(adminKey);

						if (userService.isExistingKey(adminKey)) {
							System.out.println("\nEnter the Name:");
							String adminName = xyz.nextLine();
							System.out.println("Enter the Email:");
							String adminEmail = xyz.nextLine();
							System.out.println("Enter the Password:");
							String adminPassword = xyz.nextLine();

							model.setUname(adminName);
							model.setUemail(adminEmail);
							model.setUpassword(adminPassword);

							boolean istrue;
							
							System.out.println((istrue=userService.isAddNewAdmin(model)) ? "Admin Added Successfully!"
									: "Admin NOT Added Successfully!");
							if(istrue)
							{
								logger.info("Admin Added Successfully...!");
							}
							else
							{
								logger.info("Admin NOT Added Successfully...!");
							}
						}
						
						else {
							System.out.println("Invalid Key");
						}
						
						}
						catch(InputMismatchException ex)
						{
							System.out.println("Error is:"+ex);
							logger.error("Error is in Adding User By Constomer "+ex.getMessage());
						}
						
						break;

					case 2:
						System.out.println("\nEnter the Name:");
						xyz.nextLine();
						String customerName = xyz.nextLine();
						System.out.println("Enter the Email:");
						String customerEmail = xyz.nextLine();
						System.out.println("Enter the Password:");
						String customerPassword = xyz.nextLine();

						model.setUname(customerName);
						model.setUemail(customerEmail);
						model.setUpassword(customerPassword);

						boolean iscust;
						System.out.println((iscust=userService.isAddNewUser(model)) ? "User Added Successfully!"
								: "User NOT Added Successfully!");
						if(iscust)
						{
							logger.info("Customer Added Successfully...!");
						}
						else
						{
							logger.info("Customer is NOT Added Successfully...!");
						}
						break;

					default:
						System.out.println("Invalid Choice");
					}
					System.out.println("Do you want to register another user? (yes/no)");
					limit = xyz.nextLine();

				} while (limit.equals("yes"));
				break;

			case 2:
				System.out.println(
						"===============================================================    LOGIN   ====================================================================================");
				do {
					System.out.println("1. Admin Login");
					System.out.println("2. Customer Login");
					System.out.println("3.Exit");

					System.out.println("Enter your choice:");
					int loginChoice = xyz.nextInt();

					switch (loginChoice) 
					{
					case 1: // Admin Login
						System.out.println("Enter the Admin Name:");
						xyz.nextLine();
						String adminUsername = xyz.nextLine();
						System.out.println("Enter the Password:");
						String adminPassword = xyz.nextLine();

						if (userService.isExistingAdmin(adminUsername, adminPassword)) 
						{
							logger.info("Admin Logged in SuccessFully...");
							System.out.println("\nYou are successfully logged into the Admin Panel.\n");

							do {
								System.out.println("1. Customer");
								System.out.println("2. Categories");
								System.out.println("3. Events");
								System.out.println("4. Bookings");
								System.out.println("5. View All Bookings");
								System.out.println("6. Cancel Booking");
								System.out.println("7. Booking Receipt");
								
								System.out.println("Enter your choice:");
								choice = xyz.nextInt();

								switch (choice) {
								// case 1 Customer Admin
								// ====================================================================================================================================================================
								case 1: // Customer by Admin
									do {
										logger.debug("Admin Select Customer Case");

										System.out.println("1. Add Customer");
										System.out.println("2. Show Customer");
										System.out.println("3. Delete Customer");
										System.out.println("Enter your choice");
										int cho = xyz.nextInt();

										switch (cho) {
										// Customer Add by admin
										case 1:
											try {
											System.out.println("\nEnter the Name:");
											xyz.nextLine();
											String name = xyz.nextLine();
											System.out.println("Enter the Email:");
											String email = xyz.nextLine();
											System.out.println("Enter the Password:");
											String password = xyz.nextLine();

											model.setUname(name);
											model.setUemail(email);
											model.setUpassword(password);

											System.out.println(
													userService.isAddNewUser(model) ? "Customer Added Successfully!"
															: "Customer NOT Added Successfully!");
											}
											catch(Exception ex)
											{
												System.out.println("Error Is :"+ex);
												logger.fatal("Error is :"+ex.getMessage());
											}
											break;

										// Customer Show by Admin
										case 2:
											
											List<UserModel> custList = new ArrayList<UserModel>();
											System.out.println("xxxxxxxxxxxxCustomer Listxxxxxxxxx");
											custList = userService.getAllCustomer();
											if (custList.isEmpty()) {
												System.out.println("There are no data present...");
											} 
											else 
											{
												custList.forEach((cl) -> System.out.println(cl.getUid() + "\t" + cl.getUname()+ "\t" + cl.getUemail() + "\t" + cl.getUpassword()));
											
											}
											

											break;

										case 3:
											// Customer Delete By Admin
											System.out.println("xxxxxxxxxxxxCustomer Listxxxxxxxxx");
											custList = userService.getAllCustomer();
											if (custList.isEmpty()) {
												System.out.println("There are no data present...");
											} else {
												custList.forEach(
														(cl) -> System.out.println(cl.getUid() + "\t" + cl.getUname()
																+ "\t" + cl.getUemail() + "\t" + cl.getUpassword()));
											}
											System.out.println("Enter the UserID For Delete");
											int uid = xyz.nextInt();

											model.setUid(uid);
											System.out.println(userService.isDeleteCustomer(uid)
													? "Customer Deleted Successfully...!"
													: "Customer NOT Deleted");

											break;

										default:
											System.out.println("Invalid choice");
										}

										System.out.println("Do you want to Repeat? (yes/no)");
										xyz.nextLine();
                                        limit = xyz.nextLine();
                                       // System.out.println("Final message customer End"+limit);

									} while (limit.equals("yes"));

									break;

//======================================================================================================================================================================================================
								// Category Add By(Admin)
								case 2:
									logger.debug("Admin Select Category Case");

									do {

										System.out.println("1.Add Category");
										System.out.println("2.Show Category");
										System.out.println("3.Delete Category");
										System.out.println("Enter the Choice");
										int ch = xyz.nextInt();

										switch (ch) {
										case 1:
											System.out.println("Enter The Category Name");
											xyz.nextLine();
											String category = xyz.nextLine();
											cmodel.setEcategory(category);

											System.out.println((categoryService.isAddNewCategory(cmodel))
													? "Category Added Successfully.....!"
													: "Category NOT Added Successfully.....!");

											break;
										// =================================================================================================================================================================================================
										case 2:
											System.out.println("\n CATEGORY LIST \n");
											List<CategoryModel> catlist = categoryService.getAllCategoriesList();
											catlist.forEach((cl) -> System.out
													.println(cl.getCategoryid() + "\t" + cl.getEcategory()));

											break;
										// ==================================================================================================================================================================================================
										case 3:
											System.out.println("\n CATEGORY LIST \n");
											catlist = categoryService.getAllCategoriesList();
											catlist.forEach((cl) -> System.out
													.println(cl.getCategoryid() + "\t" + cl.getEcategory()));

											System.out.println("Enter the CategoryId for Delete the Category");
											int cid = xyz.nextInt();

											boolean b = categoryService.isDeleteCategory(cid);
											if (b) 
											{
												System.out.println("Category Deleted Successfully.....!");
											} 
											else 
											{
												System.out.println("Category NOT Deleted Successfully.....!");
											}

											break;
										// ====================================================================================================================================================
										default:
											System.out.println("Invalid choice");

										}

										System.out.println("Do you want to Repeat? (yes/no)");
										xyz.nextLine();
										limit = xyz.nextLine();
										//System.out.println("Final message Category end"+limit);

									} while (limit.equals("yes"));

									break;
								// Category Admin End
								// Event Add By(Admin)
//======================================================================================================================================================================================================
								// Event case start(admin)
								case 3:
									logger.debug("Admin Select Event Case");
									do {
										System.out.println("1.Add Event");
										System.out.println("2.Show Event");
										System.out.println("3.Delete Event");
										System.out.println("4.Update Event");

										System.out.println("Enter the Choice");
										choice = xyz.nextInt();

										switch (choice) {
										// Add Event
										case 1:
											int eid;
											System.out.println("\n CATEGORY LIST \n");
											List<CategoryModel> catlist = categoryService.getAllCategoriesList();
											catlist.forEach((cl) -> System.out
													.println(cl.getCategoryid() + "\t" + cl.getEcategory()));

											xyz.nextLine();
											System.out
													.println("Enter the Category Name in which you want to add Event");
											String cname = xyz.nextLine();

											//System.out.println("cname is" + cname);
											int cid = categoryService.getCategoryIdByName(cname);
											//System.out.println("Category id is:" + cid);

											xyz.nextLine();
											System.out.println(
													"Enter name of Event,DATE(yyyy-mm-dd),Total seats,available seats,price,time and Discription of Event ");
											try {
												String name = xyz.nextLine();

												String date = xyz.nextLine();

												int tseats = xyz.nextInt();

												int aseats = xyz.nextInt();

												if(aseats<=tseats)
												{
												int price = xyz.nextInt();
												xyz.nextLine();

												String time = xyz.nextLine();

												String desc = xyz.nextLine();

												emodel.setEname(name);
												emodel.setEdate(date);
												emodel.setTseat(tseats);
												emodel.setAseat(aseats);
												emodel.setPrice(price);
												emodel.setTime(time);
												emodel.setDesc(desc);

												
												boolean b = eventService.isAddNewEvent(emodel);

												xyz.nextLine();
												System.out.println("Enter the EventName for Associate with Category");
												String ename = xyz.nextLine();
												xyz.nextLine();
											    eid = eventService.getEventIdByEventName(ename);
												//System.out.println("Event id is:" + eid);

												
												boolean bb = eventService.isAsscociatedCatEvent(cid, eid);
												
												
												//System.out.println(bb);
                                               
												if (b) {
													if (bb) {
														System.out.println("Event added successfully...");
													}
												} else {
													System.out.println("Event not Added...");
												}
												}
												else
												{
													System.out.println("Please Enter The Available Tickets Less Than Total Tickets ");
												}
											} catch (InputMismatchException e) {
												System.out.println(
														"Invalid input! Please enter data in the correct format.");
												xyz.nextLine();
												logger.error("Invalid input! Please enter data in the correct format. ::ClientApp :Event->Case 1");
											} catch (Exception e) {
												System.out.println("An error occurred: " + e.getMessage());
												logger.fatal("::ClientApp :Event->Case 1"+e);
											}
											break;
										case 2:
											System.out.println(
													"====================================================================Upcoming Events===================================================");
											elist = eventService.getAllEvent(emodel);
											elist.forEach((e) -> System.out.println("Event ID :"+e.getEid() + "\n" + "Event Name :"+e.getEname()
													+ "\n" +"Event Date :"+ e.getEdate() + "\n" +"Total Seats :"+ e.getTseat() + "\n" +"Available Seats :"+e.getAseat()
													+ "\n" +"Price :"+ e.getPrice() + "\n" +"Time :" +e.getTime() + "\n" +"Description :"+ e.getDesc()+ "\n\n"));
											break;

										case 3:
											System.out.println(
													"====================================================================Upcoming Events===================================================");
											elist = eventService.getAllEvent(emodel);
											elist.forEach((e) -> System.out.println("Event ID :"+e.getEid() + "\n" + "Event Name :"+e.getEname()
													+ "\n" +"Event Date :"+ e.getEdate() + "\n" +"Total Seats :"+ e.getTseat() + "\n" +"Available Seats :"+e.getAseat()
													+ "\n" +"Price :"+ e.getPrice() + "\n" +"Time :" +e.getTime() + "\n" +"Description :"+ e.getDesc()+ "\n\n"));
											
											System.out.println("Enter Event ID for delete:");
											 eid = xyz.nextInt();
											boolean b = eventService.isDeleteEvent(eid);
											if (b) {
												System.out.println("Event deleted successfully...");
											} else {
												System.out.println("Event not deleted...");
											}
											break;
										case 4:
											System.out.println(
													"===================Upcoming Events======================");
											elist = eventService.getAllEvent(emodel);
											elist.forEach((e) -> System.out.println(e.getEid() + "\t" + e.getEname()
													+ "\t" + e.getEdate() + "\t" + e.getTseat() + "\t" + e.getAseat()
													+ "\t" + e.getPrice() + "\t" + e.getTime() + "\t" + e.getDesc()));

											System.out.println("Enter id to update date of event");
											xyz.nextLine();
											eid = xyz.nextInt();
											System.out.println("Enter Date(yyyy-mm-dd) for update");
											xyz.nextLine();
											String date = xyz.nextLine();

											b = eventService.updateDateById(eid, date);
											if (b) {
												System.out.println("Date of Event Updated Successfully...");
											} else {
												System.out.println("Date not updated...");
											}
											break;

										default:
											System.out.println("Invalid choice");
										}

										System.out.println("Do you want to Repeat? (yes/no)");
										xyz.nextLine();
										limit = xyz.nextLine();
										//System.out.println("Final message Event case End"+limit);

									} while (limit.equals("yes"));

									break;
								// Event case End Admin
//=====================================================================================================================================================================================================
//Booking Case Start								
								case 4:
									logger.debug("Admin start Booking ");

									System.out.println("Booking Ticket...");
									LinkedHashMap<String, ArrayList<EventModel>> ecatlist = eventService
											.getAllEventByCategory();
									for (Map.Entry<String, ArrayList<EventModel>> entry : ecatlist.entrySet()) {
										String category = entry.getKey();
										ArrayList<EventModel> events = entry.getValue();

										System.out.println("Category: " + category);
										System.out.println("Events:");

										if (events != null && !events.isEmpty()) {
											for (EventModel event : events) {
												System.out.println("  Event Name: " + event.getEname());
												System.out.println("  Event Date: " + event.getEdate());
												System.out.println("  Total Seats: " + event.getTseat());
												System.out.println("  Available Seats: " + event.getAseat());
												System.out.println("  Price: " + event.getPrice());
												System.out.println("  Time: " + event.getTime());
												System.out.println("  Description: " + event.getDesc());
												System.out.println("\n");
											}
										} else {
											System.out.println("  No events in this category.");
										}
									}

									xyz.nextLine();

									System.out.println("Enter Event Name Which you want to Book...");
									String ename = xyz.nextLine();
									// xyz.nextLine();
									int eid = eventService.getIdByEventName(ename);

									if (eid != -1) {
										int availableSeats = eventService.getAvailableSeats(eid);
										System.out.println("Available seats for this Event is :" + availableSeats);

										int numtickets;
										if (availableSeats > 0) {
											System.out.println("Enter User name...");
											String uname = xyz.nextLine();
											int uid = userService.getUidByUserName(uname);

											System.out.println("eid=" + eid + "\t" + "uid=" + uid);

											if (uid != -1) {
												System.out.println("Enter Number of tickets:");
												numtickets = xyz.nextInt();

												xyz.nextLine();
												if (numtickets < availableSeats) {

													int price = eventService.getPriceByEventId(eid);
													float totalprice = bookingService.calBookingAmount(price,
															numtickets);

													String status = "";
													System.out.println("Your total amount of ticket is:" + totalprice);
													System.out.println("Are you sure to confirm your booking???(y/n)");
													String msg = xyz.nextLine();

													if (msg.equals("y")) {
														System.out.println("Enter " + totalprice
																+ " amount to complete transaction:");
														float uamt = xyz.nextFloat();
														// xyz.nextLine();
														if (totalprice == uamt) {
															System.out
																	.println("Your tickets is Successfully Booked...");

															status = "Confirm";
															boolean b = eventService.updateAvailableSeats(eid,
																	numtickets);
														} else if (uamt == 0) {
															System.out.println(
																	"Transaction Failed...Plz Enter total price...");
															xyz.nextLine();
															status = "Failed";
														} else {
															System.out.println("Your Booking status is pending...");

															status = "Not Confirm";
														}

														bmodel.setUid(uid);
														bmodel.setBuname(uname);
														bmodel.setEid(eid);
														bmodel.setBename(ename);
														bmodel.setNumtickets(numtickets);
														bmodel.setStatus(status);
														bmodel.setFinalprice(totalprice);

														boolean b = bookingService.isAddBookingDeatails(bmodel);

														if (b) {
															System.out.println("\nYour Booking Status is ..."
																	+ bmodel.getStatus());
														} else {
															System.out.println("Booking not done...");
														}
													}

													else {
														System.out.println("Thank you for visiting Us....!");
													}
												}
											}

											else {
												System.out.println("Inavlid UserName");
											}
										} else {
											System.out.println("All Seats Are Booked For This Event");

										}
									} else {
										System.out.println("Please Enter the valid Event Name");
									}
									xyz.nextLine();
							break;

// =========================================================================================================================================================================================================
									case 5:
									System.out.println("==================================All Booking List==================================");
								
									bklist=bookingService.getAllBookings();
								
									bklist.forEach((bk)->System.out.println("\nBookingID :"+bk.getBkid()+"\nUserID :"+bk.getUid()+"\nUserName :"+bk.getBuname()+"\nEventId :"+bk.getEid()+"\nEventName :"+bk.getBename()+"\nNumTickets :"+bk.getNumtickets()+"\nBooking Date :"+bk.getBkdate()+"\nStatus: "+bk.getStatus()+"\nFinal Amount :="+bk.getFinalprice()));

									xyz.nextLine();
							break;
									
//===================================================================================================================================================================================================									
									case 6:
										
										System.out.println("Enter UserName :");
										xyz.nextLine();
										String buname=xyz.nextLine();
									
									    System.out.println("==================================Your Booking List ==================================================");
										
										ubklist=bookingService.getBookingByUserName(buname);
									
										ubklist.forEach((ubk)->System.out.println("\nBookingID :"+ubk.getBkid()+"\nUserID :"+ubk.getUid()+"\nUserName :"+ubk.getBuname()+"\nEventId :"+ubk.getEid()+"\nEventName :"+ubk.getBename()+"\nNumTickets :"+ubk.getNumtickets()+"\nBooking Date :"+ubk.getBkdate()+"\nStatus: "+ubk.getStatus()+"\nFinal Amount :="+ubk.getFinalprice()));
	                                    
										System.out.println("Enter the Booking ID which You Want To Cancel Your Booking");
										int bkid=xyz.nextInt();

										eid=bookingService.getEidByBookingId(bkid);
										//System.out.println("EID  "+eid);
										int numtickets=bookingService.getNumTicketsByBookingId(bkid);
										//System.out.println("numtickets  "+numtickets);
										
										boolean b=bookingService.isCancelBooking(bkid);
										if(b)
										{
											System.out.println("Your Booking Canceled Successfully...!");
											
											b=eventService.isAddAvailableSeats(eid,numtickets);
										}
										else
										{
											System.out.println("There is Some Problem for Cancellation...!");
										}
										xyz.nextLine();
										
							break;
									
//===================================================================================================================================================================================================									
//receipt									
							            case 7:
										System.out.println("Enter UserName :");
										xyz.nextLine();
										buname=xyz.nextLine();
									
									    System.out.println("=======================================Your Booking List ==================================================");
										
										ubklist=bookingService.getBookingByUserName(buname);
									
										ubklist.forEach((ubk)->System.out.println("\nBookingID :"+ubk.getBkid()+ubk.getUid()+"\nUserName :"+ubk.getBuname()+"\nEventName :"+ubk.getBename()));
	                                    
										System.out.println("Enter the Booking ID For Getting Receipt");
									    bkid=xyz.nextInt();
									    
									    reciptlist=bookingService.getBookingById(bkid);
										
									    System.out.println("========================================Your Receipt======================================================");
									    reciptlist.forEach((cbk)->System.out.println("\nBookingID :"+cbk.getBkid()+"\nUserID :"+cbk.getUid()+"\nUserName :"+cbk.getBuname()+"\nEventId :"+cbk.getEid()+"\nEventName :"+cbk.getBename()+"\nNumTickets :"+cbk.getNumtickets()+"\nBooking Date :"+cbk.getBkdate()+"\nStatus: "+cbk.getStatus()+"\nCGST:9% "+"\nSGST:9% "+"\n\n Final Amount :="+cbk.getFinalprice()));
									    System.out.println("==================================================XXX ==================================================");
										
							            xyz.nextLine();
										break;
										
//======================================================================================================================================================================================================
									default:
									System.out.println("Invalid Choice");
								}

								System.out.println("Do you want to perform another action? (yes/no)");
								//xyz.nextLine();
								limit = xyz.nextLine();
								//System.out.println("Final message Admin case End"+limit);
							} while (limit.equals("yes"));
						} 
						else 
						{
							System.out.println("Invalid Admin Credentials");
							logger.info("Invalid Admin Password or Username");
						}

		 break;

//==================================================================================================================================================================================================================    

					case 2: // Customer Login
						System.out.println("Enter the Username:");
						xyz.nextLine();
						String customerUsername = xyz.nextLine();
						System.out.println("Enter the Password:");
						String customerPassword = xyz.nextLine();

						if (userService.isExistngUser(customerUsername, customerPassword)) 
						{
							logger.info("Customer Logged in SuccessFully...");
							System.out.println("\nYou are successfully logged into the Customer Panel.\n");
							do {
								System.out.println("1.View Events");
								System.out.println("2.Book Ticket");
								System.out.println("3.Cancel Booking ");
								System.out.println("4.Receipt");
								System.out.println("Enter your choice:");
								int ch = xyz.nextInt();

								switch (ch) {
								case 1:
									LinkedHashMap<String, ArrayList<EventModel>> ecatlist = eventService.getAllEventByCategory();
									for (Map.Entry<String, ArrayList<EventModel>> entry : ecatlist.entrySet()) {
									    String category = entry.getKey();
									    ArrayList<EventModel> events = entry.getValue();

									    System.out.println("Category: " + category);
									    System.out.println("Events:");

									    if (events != null && !events.isEmpty()) {
									        for (EventModel event : events) {
									            System.out.println("  Event Name: " + event.getEname());
									            System.out.println("  Event Date: " + event.getEdate());
									            System.out.println("  Total Seats: " + event.getTseat());
									            System.out.println("  Available Seats: " + event.getAseat());
									            System.out.println("  Price: " + event.getPrice());
									            System.out.println("  Time: " + event.getTime());
									            System.out.println("  Description: " + event.getDesc());
									            System.out.println("\n");
									        }
									    } else {
									        System.out.println("  No events in this category.");
									    }
									}
									break;
//=================================================================================================================================================================================================
								case 2:
									System.out.println("Booking Ticket...");
									ecatlist = eventService.getAllEventByCategory();
									for (Map.Entry<String, ArrayList<EventModel>> entry : ecatlist.entrySet()) {
										String category = entry.getKey();
										ArrayList<EventModel> events = entry.getValue();

										System.out.println("Category: " + category);
										System.out.println("Events:");

										if (events != null && !events.isEmpty()) {
											for (EventModel event : events) {
												System.out.println("  Event Name: " + event.getEname());
												System.out.println("  Event Date: " + event.getEdate());
												System.out.println("  Total Seats: " + event.getTseat());
												System.out.println("  Available Seats: " + event.getAseat());
												System.out.println("  Price: " + event.getPrice());
												System.out.println("  Time: " + event.getTime());
												System.out.println("  Description: " + event.getDesc());
												System.out.println("\n");
											}
										} else {
											System.out.println("  No events in this category.");
										}
									}

									xyz.nextLine();

									System.out.println("Enter Event Name Which you want to Book...");
									String ename = xyz.nextLine();
									// xyz.nextLine();
									int eid = eventService.getIdByEventName(ename);

									if (eid != -1) {
										int availableSeats = eventService.getAvailableSeats(eid);
										System.out.println("Available seats for this Event is :" + availableSeats);

										int numtickets;
										if (availableSeats > 0) {
											System.out.println("Enter User name...");
											String uname = xyz.nextLine();
											int uid = userService.getUidByUserName(uname);

											System.out.println("eid=" + eid + "\t" + "uid=" + uid);

											if (uid != -1) {
												System.out.println("Enter Number of tickets:");
												numtickets = xyz.nextInt();

												xyz.nextLine();
												if (numtickets < availableSeats) {

													int price = eventService.getPriceByEventId(eid);
													float totalprice = bookingService.calBookingAmount(price,
															numtickets);

													String status = "";
													System.out.println("Your total amount of ticket is:" + totalprice);
													System.out.println("Are you sure to confirm your booking???(y/n)");
													String msg = xyz.nextLine();

													if (msg.equals("y")) {
														System.out.println("Enter " + totalprice
																+ " amount to complete transaction:");
														float uamt = xyz.nextFloat();
														// xyz.nextLine();
														if (totalprice == uamt) {
															System.out
																	.println("Your tickets is Successfully Booked...");

															status = "Confirm";
															boolean b = eventService.updateAvailableSeats(eid,
																	numtickets);
														} else if (uamt == 0) {
															System.out.println(
																	"Transaction Failed...Plz Enter total price...");
															xyz.nextLine();
															status = "Failed";
														} else {
															System.out.println("Your Booking status is pending...");

															status = "Not Confirm";
														}

														bmodel.setUid(uid);
														bmodel.setBuname(uname);
														bmodel.setEid(eid);
														bmodel.setBename(ename);
														bmodel.setNumtickets(numtickets);
														bmodel.setStatus(status);
														bmodel.setFinalprice(totalprice);

														boolean b = bookingService.isAddBookingDeatails(bmodel);

														if (b) {
															System.out.println("\nYour Booking Status is ..."
																	+ bmodel.getStatus());
														} else {
															System.out.println("Booking not done...");
														}
													}

													else {
														System.out.println("Thank you for visiting Us>>>>!");
													}
												}
											}

											else {
												System.out.println("Inavlid UserName");
											}
										} else {
											System.out.println("All Seats Are Booked For This Event");

										}
									} else {
										System.out.println("Please Enter the valid Event Name");
									}
									
									break;
//==================================================================================================================================================================================================
								case 3:
									System.out.println("Cancel Booking");
									
									System.out.println("Enter UserName :");
									xyz.nextLine();
									String buname=xyz.nextLine();
								
								    System.out.println("==================================All Booking List BY Username==================================");
									
									ubklist=bookingService.getBookingByUserName(buname);
								
									ubklist.forEach((ubk)->System.out.println("\nBookingID :"+ubk.getBkid()+"\nUserID :"+ubk.getUid()+"\nUserName :"+ubk.getBuname()+"\nEventId :"+ubk.getEid()+"\nEventName :"+ubk.getBename()+"\nNumTickets :"+ubk.getNumtickets()+"\nBooking Date :"+ubk.getBkdate()+"\nStatus: "+ubk.getStatus()+"\nFinal Amount :="+ubk.getFinalprice()));
                                    
									System.out.println("Enter the Booking ID which You Want To Cancel Your Booking");
									int bkid=xyz.nextInt();

									eid=bookingService.getEidByBookingId(bkid);
									
									int numtickets=bookingService.getNumTicketsByBookingId(bkid);
									
									
									boolean b=bookingService.isCancelBooking(bkid);
									if(b)
									{
										System.out.println("Your Booking Canceled Successfully...!");
										
										b=eventService.isAddAvailableSeats(eid,numtickets);
									}
									else
									{
										System.out.println("There is Some Problem for Cancellation...!");
									}
									
									
									
									break;
//==================================================================================================================================================================================================								
								case 4:
									System.out.println("Enter UserName :");
									xyz.nextLine();
									buname=xyz.nextLine();
								
								    System.out.println("=======================================Your Booking List ==================================================");
									
									ubklist=bookingService.getBookingByUserName(buname);
								
									ubklist.forEach((ubk)->System.out.println("\nBookingID :"+ubk.getBkid()+ubk.getUid()+"\nUserName :"+ubk.getBuname()+"\nEventName :"+ubk.getBename()));
                                    
									System.out.println("Enter the Booking ID For Getting Receipt");
								    bkid=xyz.nextInt();
								    
								    reciptlist=bookingService.getBookingById(bkid);
									
								    System.out.println("========================================Your Receipt======================================================");
								    reciptlist.forEach((cbk)->System.out.println("\nBookingID :"+cbk.getBkid()+"\nUserID :"+cbk.getUid()+"\nUserName :"+cbk.getBuname()+"\nEventId :"+cbk.getEid()+"\nEventName :"+cbk.getBename()+"\nNumTickets :"+cbk.getNumtickets()+"\nBooking Date :"+cbk.getBkdate()+"\nStatus: "+cbk.getStatus()+"\nCGST:9% "+"\nSGST:9% "+"\n\n Final Amount :="+cbk.getFinalprice()));
								    System.out.println("==================================================XXX ==================================================");
									
		
									break;
//===================================================================================================================================================================================================

								default:
									System.out.println("Invalid Choice");
								}
								System.out.println("Do you want to perform another action? (yes/no)");
								xyz.nextLine();
								limit = xyz.nextLine();
							} while (limit.equals("yes"));
						} 
						else 
						{
							System.out.println("Invalid Customer Credentials");
						}
						break;

					}
					System.out.println("Do you want to perform another action? (yes/no)");
					//xyz.nextLine();
					limit = xyz.nextLine();
					//System.out.println("Final message ad login"+limit);
				} while (limit.equals("yes"));
//===================================================================================================================================================================================================
			case 3:
				System.out.println("Thank you for using the Ticket Reservation App. Goodbye!");
				xyz.close();
				System.exit(0);

				break;
				
			default:
				System.out.println("Invalid Choice");
			}
			
			System.out.println("Do you want to perform another action? (yes/no)");
			//xyz.nextLine();
			limit = xyz.nextLine();
			//System.out.println("Final message 1"+limit);
		} while (limit.equals("yes"));
		}
		catch(InputMismatchException ex)
		{
			System.out.println("Error is :"+ex.getMessage());
			logger.error("Error Is Occured When Giving Wrong Input:");
		}
	}
}
