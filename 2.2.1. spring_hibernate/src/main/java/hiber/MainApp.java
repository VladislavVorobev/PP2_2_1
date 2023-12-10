package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Opel",5)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("Astra",6)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Beha",3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Volvo",4)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+(user.getCar()).getModel());
         System.out.println("Car series = "+(user.getCar().getSeries()));
         System.out.println(" ");
      }

      context.close();

      List<User> usersByCars = userService.findUserByCar("BMW", 5);
      for (User user : usersByCars) {
         System.out.println("User with Id = "+user.getId());
         System.out.println("And First Name = "+user.getFirstName());
         System.out.println("And Last Name = "+user.getLastName());
         System.out.println("Have car: "+ user.getCar().getModel());
         System.out.println("With series = "+ user.getCar().getSeries());
         System.out.println(" ");
      }


      context.close();
   }
}
