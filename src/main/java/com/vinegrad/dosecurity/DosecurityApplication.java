package com.vinegrad.dosecurity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.vinegrad.dosecurity.model.Authority;
import com.vinegrad.dosecurity.model.Item;
import com.vinegrad.dosecurity.model.User;
import com.vinegrad.dosecurity.repository.AuthorityRepository;
import com.vinegrad.dosecurity.repository.ItemRepository;
import com.vinegrad.dosecurity.repository.UserRepository;

@SpringBootApplication
public class DosecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepository uRepository;
	@Autowired
	private AuthorityRepository aRepository;
	@Autowired
	private ItemRepository iRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(DosecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<User> users = Arrays.asList(new User("jake", "$2a$10$tTmq4xnn3CtwBcaOuuqiWeGi6I9IciXAlFBzzQk.42klU911wZkIC", true));
		List<Authority> authorities = Arrays.asList(new Authority("jake", "ROLE_ADMIN"), new Authority("jake", "ROLE_USER"));
		List<Item> items = Arrays.asList(
				new Item("Italian Salad", "starter", 3.50, "A very healthy option which consists of fresh batavia lettuce, basil and mozzarella balls, served with a rich balsamic dressing.","https://img.taste.com.au/EU9D0DbT/w720-h480-cfill-q80/taste/2016/11/ripper-thai-beef-noodle-salad-62642-1.jpeg"),
				new Item("Calamari", "starter", 3.75, "Fried arctic squid covered in delicious lemon-scented breadcrumbs with a thousand island dipping sauce.","https://cdn-image.myrecipes.com/sites/default/files/styles/4_3_horizontal_-_1200x900/public/image/recipes/cl/03/05/calamari-cl-434145-x.jpg?itok=BSVW0Np0"),
				new Item("Cream of Mushroom Soup", "starter", 3.99, "A hot and creamy mushroom soup with diced button mushrooms and fresh parsley, served with a scottish roll.", "https://144f2a3a2f948f23fc61-ca525f0a2beaec3e91ca498facd51f15.ssl.cf3.rackcdn.com/uploads/food_portal_data/recipes/recipe/hero_article_image/682/letterbox_resizeimage593x426xvR5DzyDiB8.jpg"),
				new Item("Tower Burger", "main", 5.5, "Your choice of Aberdeen angus beef or lean chicken, in a toasted brioche bun with maple bacon and fresh salad. Served with thick chips and onion rings.", "https://c1.staticflickr.com/5/4116/4808488778_2311a32c38_b.jpg"),
				new Item("Burrito", "main", 5.40, "Say arriba to our all-mexican burrito! A flour burrito stuffed with pulled pork, sour cream, rice, salsa and our famous guacamole.", "https://img.buzzfeed.com/thumbnailer-prod-us-east-1/2591b55a908b478abf921cadfaf8978e/BFV35429_FlamingHotCheetoBurrito_FB.jpg?output-quality=60&resize=1000:*"),
				new Item("Steak and Ale Pie", "main", 5.30, "Chunks of locally farmed sirloin steak in a London Pride ale. Comes with chips, peas and onion rings.", "https://www.booths.co.uk/wp-content/uploads/beef_and_ale_pie_960x557-660x371.jpg"),
				new Item("Chocolate Fudge Cake", "dessert", 4, "A classic for anyone with a sweet tooth. Insanely chocolatey cake served with vanilla gelato.", "https://assets.bonappetit.com/photos/57acc6151b33404414975196/16:9/w_1200,c_limit/chocolate-fudge-cake.jpg"),
				new Item("Super Sundae", "dessert", 4.5, "Three scoops of your choice of ice-cream, topped with warm fudge, nuts and cookie dough pieces.", "https://i.gifer.com/7f65.gif"),
				new Item("Ice cream", "dessert", 3.50, "3 scoops of delicious light ice cream. Your choice of vanilla, chocolate or strawberry.", "https://www.seriouseats.com/2018/06/20180625-no-churn-vanilla-ice-cream-vicky-wasik-13-1500x1125.jpg"),
				new Item("Pop", "drink", 2, "Choose from coke, diet coke, fanta, sprite and tango.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8FYhUzL--woZkoqs9zPyjGEwuUo2PcA6w5xceFY4lDcWZePNxBA"),
				new Item("Beer", "drink", 3.5, "Choose from Stella, Budweiser and John Smith.", "https://cdn.mos.cms.futurecdn.net/wW6tbeyaiUGuHDrSmPPvzj-768-80.jpg"),
				new Item("Wine", "drink", 4, "House red, white or rose.", "https://media.giphy.com/media/3zfox4LvqnIas/giphy.gif")
				);
		users.forEach(user -> uRepository.save(user));
		authorities.forEach(authority -> aRepository.save(authority));
		items.forEach(item -> iRepository.save(item));
	}
}
