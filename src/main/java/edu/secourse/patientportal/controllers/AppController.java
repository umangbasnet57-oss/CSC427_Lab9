package edu.secourse.patientportal.controllers;
import edu.secourse.patientportal.models.
*;
import edu.secourse.patientportal.services.
*;
public class AppController {
private final AuthenticationService authService;
private final UserService userService;
private final AppointmentService appointmentService;
private User currentUser;
public AppController(AuthenticationService authService,
UserService userService,
AppointmentService appointmentService) {
this.authService = authService;
this.userService = userService;
this.appointmentService = appointmentService;
}
// AUTHENTICATION
public boolean login(String username, String password) {
User user = authService.authenticate(username, password);
if (user != null) {
currentUser = user;
return true;
}
return false;
public void logout() {
currentUser = null;
public boolean isLoggedIn() {
return currentUser != null;
public User getCurrentUser() {
return currentUser;
}
}
}
}
// USER FUNCTIONS
public boolean updateEmail(String newEmail) {
if (!isLoggedIn()) return false;
currentUser.setEmail(newEmail);
return true;
}
// APPOINTMENTS
public Appointment createAppointment(Patient patient, Doctor doctor) {
if (!isLoggedIn()) return null;
return appointmentService.createAppointment(patient, doctor);
}
public boolean cancelAppointment(int id) {
if (!isLoggedIn()) return false;
return appointmentService.cancelAppointment(id);
}
public boolean rescheduleAppointment(int id) {
if (!isLoggedIn()) return false;
return appointmentService.rescheduleAppointment(id);
}
}
AppControllerTest.java
package edu.secourse.patientportal.controllers;
import edu.secourse.patientportal.models.
*;
import edu.secourse.patientportal.enums.Roles;
import edu.secourse.patientportal.services.
*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.
*;