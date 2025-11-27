
package edu.secourse.patientportal.controllers;
import edu.secourse.patientportal.models.
*;
import edu.secourse.patientportal.enums.Roles;
import edu.secourse.patientportal.services.
*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.
*;
public class AppControllerTest {
@Test
public void testLoginSuccess() {
AuthenticationService auth = new AuthenticationService();
UserService us = new UserService();
AppointmentService as = new AppointmentService();
Patient p = new Patient(1,
"punika"
,
"pass123"
,
"Punika"
,
"punika@example.com"
,
Roles.Patient);
us.createUser(p);
auth.register("punika"
,
"123");
AppController controller = new AppController(auth, us, as);
boolean loggedIn = controller.login("punika"
,
"123");
assertTrue(loggedIn);
assertEquals("punika"
, controller.getCurrentUser().getUsername());
}
@Test
public void testLoginFail() {
AuthenticationService auth = new AuthenticationService();
UserService us = new UserService();
AppointmentService as = new AppointmentService();
AppController controller = new AppController(auth, us, as);
assertFalse(controller.login("wrong"
,
assertFalse(controller.isLoggedIn());
"bad"));
}
@Test
public void testCreateAppointmentWhenLoggedIn() {
AuthenticationService auth = new AuthenticationService();
UserService us = new UserService();
AppointmentService as = new AppointmentService();
Patient p = new Patient(1,
Doctor d = new Doctor(2,
"u"
,
"d"
,
"pw"
,
"pw2"
,
"User"
,
"Doc"
,
"u@u.com"
, Roles.Patient);
"d@d.com"
, Roles.Doctor);
us.createUser(p);
auth.register("u"
,
"pw");
AppController controller = new AppController(auth, us, as);
controller.login("u"
,
"pw");
Appointment appt = controller.createAppointment(p, d);
assertNotNull(appt);
assertTrue(appt.getAppointmentId() > 0);
}
@Test
public void testCreateAppointmentWithoutLogin() {
AuthenticationService auth = new AuthenticationService();
UserService us = new UserService();
AppointmentService as = new AppointmentService();
AppController c = new AppController(auth, us, as);
Appointment appt = c.createAppointment(
new Patient(), new Doctor()
);
assertNull(appt);
}
}