package com.vinegrad.dosecurity.controller;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vinegrad.dosecurity.model.Authority;
import com.vinegrad.dosecurity.model.Item;
import com.vinegrad.dosecurity.model.User;
import com.vinegrad.dosecurity.service.UserService;

@Controller
@SessionAttributes({"seeMenu", "myMessage", "currentUser", "homeImage", "homeLink", "trump", "images", "sending", "titleSending"})
public class UserController {

	int x = 0;
	int y = 0;

	protected String message() {
		List<String> messages = Arrays.asList(
				"Welcome to the best restaurant in the Western Hemisphere.",
				"Come for a beautiful view of the city.",
				"We always ensure our guests get the wonderful experience they deserve.",
				"Our food is considered among the best in the world.",
				"Our head chef will make sure your food is cooked to the highest of standards.",
				"Many distinguished guests regularly come here.",
				"Ronaldo ate here moments before Real Madrid won the Champions League Final.",
				"So make fine dining great again!",
				"And always eat your pizza like this, or don't bother coming."
				);
		if (y == messages.size()) {
			y = 0;
		}
		return messages.get(y++);
	}
	
	public String randomImage() {
		List<String> images = Arrays.asList(
				// exterior
				"http://www.flauminc.com/server15-cdn/2016/06/07/best-restaurant-exterior-design-ideas-on-9adc65d184a2cd2b.jpg",
				// interior
				"https://comelite-arch.com/wp-content/uploads/2018/04/How-to-Create-a-Successful-Fine-Dining-Restaurant-0.jpg",
				// customers
				"http://fiveflies.co.za/wp-content/uploads/sites/19/2018/01/restaurant-customers-dining-300x169.jpg",
				// food
				"https://www.foodnewsfeed.com/sites/foodnewsfeed.com/files/styles/fnf_feature_front/public/story-images/summer-fancy-food-show-named-top-new-york-event.jpg",
				// chef ramsay
				"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxAQEA8PDw8PDxAPDw8PDw0PDw8PDw8PFhEWFhURFRUYHSghGBolGxUVITEhJSkrLi8uFx8zODMsNygtLisBCgoKDg0OFxAQGC0lIB8tListLS0rLSstLS8tLS0tLS0rKy0tLS0tLSstLSsrKysrKy0rLS0tLS0tKy0tLS0tLf/AABEIAKwBJAMBIgACEQEDEQH/xAAbAAADAAMBAQAAAAAAAAAAAAAAAQIEBQYDB//EADgQAAICAQIEBAQEBAYDAQAAAAABAhEDBCEFEjFBE1FhcQYigaFCkbHBByMy4RRSYoLw8TRD0RX/xAAZAQEBAQEBAQAAAAAAAAAAAAAAAQIEAwX/xAAjEQEBAAICAgICAwEAAAAAAAAAAQIRAyESMQRBMlEiYXET/9oADAMBAAIRAxEAPwDoWgLBo9Hmglo9KBog8gosRRNBQ6AixDE0XQUF081EGi6E0E0lo5n4g1s1J4vEcOZpKKqPiR2+WM+z/X3NxxhS8NuLlGS3Tj6fX9Dk+PSjklCOX+ZOO0qbim/K1/zr03Obmz106OHj3212DjDw5LjjmkrXieHKbg/9C35W/M3PCuLT1Uv/AAc8oVvOCcct3X4dn9UzPhqPBwRUXp8c23KC5cs7pb9Npd7fu7N9g1sHp458iWNzxOcnhbUVBOlyro5SfTbv5debq/To7jVcVzQWGMJZ9TinsovLGMXFdEpVv9bRq+DOTlLDkuU3spylLGk/WMY9H2bbT82c7xP4tnkyuKx41iT+XFHJLJa/15F191RgZ/iHUKHLBcsN14TTnGG/Zy6Lf9fM14VOnXcSzS0z8PJjnv8ANjmsrksc6uuaPZ11+x5cM+LYqcceZbSbrLVe1rz82tjn+FaNZP5ur1bxxe6wYYrJmn/uk6h7t/Q3OXBhhG8OnwrssuTUQz5b79E4xftfUsvh9pcZl9Ou0GuhmjzQdq6fZoyThPh7iEtPmlDkyZ45ZU+RLnhVu6dX1o7mErSaumr3TT/J9Dswy8o5c8fG6UAAbYAAABQqKFQAAAQAAMoQihACGAAAhgBsmKi6FRUQJjaFQRNBRZJFTQmVQqCpBlNA0BAFUKgMXWaZTi7ly0nT2pPs37HF8V0rlLUZIuONKVyyP+mPzVyr6K37o7nVQuE10uEqfk62Z8+4dJZovDqJvHHLBRd7cmZTcouT8n8qfpZyc+Pcrq4L1YyuGqDjCOKHNSTlmzrxLp9eV7N+nReV7m+XC56u4Tcl4zi883s3jitsfon1f0RlcL0EY4ouvmSS26X39zf6DCopOvmlbfp5I5/t3ySR4cP+DtDjjywwY7dXL8T9bNVxz+HOGcZLBJxlN3Lntr0O20zaW6f6lSkndOK273t9D0eFt2+UZP4V4oxuWpyOSX4UlFM5Dimj1fDppwnLJik6VuUoPfp6M+6a/pW/1RyPHdLGeKcJLZwn9G/L1J52e2/CZTr25n4ZTnqU5Y+WeNPmi2203FNSXpTs7Vmkjqsb1kY46uUvDuL3eLBgjCUn/utf7TeUdXB+Lg57/IgHQ0ezxS0OhsaCbQCKoSQUUKihhNoodDKoG0pDaAYQUHKMZAqAdABnCKaFRVS0IugobR5gXRIEiZZNBYhiZbRLQUgAGAj5nxh+HPLGXL80p8vK7anfSW2zps+mM03x98Pxy4lnhBuTytS5Fuo3y2/Pp9zn5rqx1/GwmUy/afhHUOeGNvfv6UdTLUwxpc19OkVcjm/gvQ3i+VpNKKa9a/uZXGuEa2S5cGWOK6bm7c5/Mrg5K/DjV7pN+xySbrsyuo3/AAvjePK3Hw8mNrosi5W151Zto5UcZovhaMccZTnk8VQTnkjOW+bu031j06q+pt+J4+fS8ilKDaUXkj/XGPdq+9Gt2MeMvp48b49CGVYo4Z55VcvCa+Re3c0fFNTHJCTSlF8rbhNcsmn3oxtb8Ey/lTwajKqkpZZqeRZZrnv5JJ7Pl+Xe13oyZcFz+FzajIpTgp1ka5Zyh2U0tnKq32JlPvbWN+nL/wAOtPF+JkauajFJ9aUpSb/RHbHP/BWheLDKUus5PbyS2S+x0VHdx/i+ZzTWdiGFFUOj0eSKHRSGBKAoVEEjCh0BNDCh0AqGhhQANAx0VdFQFAFZ5JYUE6Q0JlMkhCEyhBU0JooTZRLQmUxNARQDoKAho3GDHOeFuHKnVpTTcHOOzTrpfKvsalnlr+NZNHp82WEFl5IuXhSbjfZ79jy5cfKPbhz8b/rW/CWXlm4rpP5vybR3eHKqW1s+W/CGvcpwlJKLcsilFXUW3dI+k6Se1+mxxTqvock2fFsjUFW7bSrsr8zX5VJ6afTo7fk6MjiGJ5IUss8UuZSWSCg5Lqukk10ZzGo4VrVHwseu5scubmyZMSeVJeXLUfsWmM6dTwq3hg3tJLfye9Waz4n1PLgzXtUJNfkVwesWJY3lnln1WSajGXRJKopKtjV/F83PEoLrkqNe8lH9yf0era9cGNRhiS2/lw97at39WXQKFJJdEqX0GfQxmpI+VyZeWVpUJlUOjTBUJDG0F0TQikh0F0hgXQNBNICiqGDSUOhpDoLoqAdAAIB0AGexFNCIyhg0UDQWPNiLokKQmUICWhFCZQiSgoCKPHV6fxMeTHV88JRqru15GQze/D/Dm/EnOLTpLHap+r/QlWPkHDU9PmhJ/wBGbknF1/7I9Y/WP6H1PS5F8u+0lcX2NX8WfB6eKbxJ8jfiQa64Z3f0V9H22NR8N8dUo/4TM1DU4+ieyypfih+8eq9jguNl1X1dzPHyxdFr9DOVyjnyQ9IRxOKXtKLs1Oo4blWz12qSa3SjhT9afLt9DfYNTap9fUc3F7VHo23tsFxysafhfDI47nOeabWylkySbUfZbfYx8/8ANyp/hg+aK9LfL99/oj34truWKxQa58r5I26XMydNh5IpXb7y83VfsenFh5Zb/Tw+RyeM1916UKihHY+eQxUMBAhkNgUCJvYaAoAAAAKGAACHQCChgAANAQZ7EWIMoYihAJolosQWPMR6NE8oVDFRQMogcYttJJtvZJbts9tNp5ZGoxVs6fh2gji6RTl3lt9iWrpj8H4NyNZMm8+0e0f7m7jBdhQv1+pSZlSSrb7HG/Ff8O9NrG8mNvT5eqlFXBy7OuqfqmjtXuFEsl9t4Z5YXcr41q4cU0EHizuOTlf8rUTj4kMkf8rkqfN70zH0XGs8neVY3B7PkU4tfdn2TiGgx58c8WWPNCaqS/dPs/U+OajhObDknh5MkuTJOCkscnzKMmlJUu6VnLyYXH07+Dkx5JZfbltbkz5ddhnGUrjqcawrpGCc10X2ffc+p6Hh2fJBScIqfeCltXZpmu4L8KZHm0+aWGUYY8jyTc4uNJQk1SfV83KfQNBpHijGL3pbP9j24t6cvydeXTi8uKUW4yTi12aog7rXaGGWNSim/P8AuaeXAYP5U5Qfn1T90/8A6e+3NpzgGZxDQTwS5Zbp7xkukkYhUIlosVAKhhQwEMBgKh0A0AJAx0FAKhgxpAIChAbAVFMRGUNCZZBVIKG0KgpNEtFAQeZlaHQvK9ui6s8I47aS6t0dTocShBRiq2692/MVXto9FDGqivd92ZDiEEeiZlUQl5ltETj3HCXYBxfb8i0Q0NP8wGxSXqOwQESIa9N1tfoVPsgaAF/z1IljT2fXs+5SX9gfmBjajTxyR5MkVKvP9U+xynFeGvA7vmhJun3XoztZJS90Y2fApxcZK01TTLscGBncV0Pg5KW8Zbxfeu6ZhUaZFCGh0AhoBkANCQygGIYBQwAAAAAzxDERkmIAKpWJlMkKQgBgZHD4Xkj5bv8AL/tHU4kabR6Wo4Zd5RlJ/Vr9jdYehitPWKL+5MSqIEn/ANeQqK/5ZK/6KKE/MYAVQpDs8nmj5p+2/wCgFVuOjw/xK7KT9ov9zyy62STrFNvybSAykr+gka3h2uyznKOTD4XeDU+dTXdPZUzZJJVXQBdGNr9f3BoMfT8/1A1HxDpebHaVyi7jXWu/2OTO/wAkLOO4tpfCzSiujqUfZ/3ssSsJIdABpCoaAaAKAaAAoKGgoBDoYAADADNYmUySMkS0UxFXaWJlCaC7SxFMQHQ8KyKeGK74/lfp5fY2ONmg4DqFzzx3u4RbXnUnT/J19DeQ2PNqMhFkRKQAyJHohNBQAo+Qwg9xr2JY0UCe4SJfUtgTQpFEsCVLoOPRHnkZ6x6IBNGr4vw9ZV5Sj/S/2fobWTSTbdJdW+hptVx7BGXL88r/ABRj8q+rolyk9tTG5eo5mcGm4tU02mvJk0b/AIzixZEpwnDnaUlG0pTg+mxoaNy7Ys0VDAaKhDQIKAYAMAAAIGAABmUJlCYZSAAAkJjE0BIhsRVVgm4yjkXXF89+cek4/lTOqw5E907Tpxfmmcpj2aakoyTpc28JXtyvye/X3NzwTUXF42nF42406/p7VXbf7Hnfbc9N5As8oM9QGKwAoiRUZWDRMFT9yCmNAxJgJ9WOLIb3e1Lan5vuUluUUT7lSR4zyK67geTyxkuaMlKO6Ul0dPseuXNGEOabpL7+i9TA12rWNLa3slFd2zU6rVTm057qP9MFtFevueeWenrx8Vy7+j4xrZSXPJuMF/TDf6N+bNJqIyyuLyXjh2xx2lJ/6n29l+Zk58ly5skrS/ogt69X5swtXqpS+WMJOPV71L29O+5zZZb9u7DGSdPLmVyeNVjinCWRd62qP+ajLnxDDllFQfzuLU1+Fzjs2n5tbte5rddnyZcaxRhHFBUmsbp8q7RbXymJq800oqGDw4465ORpuNd15jDl8b0nJw/9J37b8EYPC9esyaarJCuePZrtJen6Ged+OUym4+bljcbqkhgBWTAAAYANECGAAZbBAwDJCGJgIBiAlokqQmVRCTTi12ktvqZsZPHmvpzR7ea3/SzXzWz9jYcRXyQl3jTT9TzzbxdJilaR7I1/DJt44N+SNjBANIOUpDZRHKRKJ567O4RbST97OA+L/jPV6fBmyYfCjLGly3j5l9bZjLOR6Ycdy9On1mbLGbi5Sdd9lt57GNHiGVdJv6019zA+Fdbl1ejwanUTeTJktv5YRjFbbRUUtvezarGm3as8bvfVdGpOrCXF8zVVDrs+V7/c+Z/xL45merhiWbUR5cLyZMePJkxQk3SUIU0q2tvrcuvY+oxgvJHO/EvAdNkljzZMXNk5mubmlGSqPaUWmttqui+Vntm4431HB8F+O9RhjjhGWaOGM8eKUJ5pajmhkl8uaE5NtO7XLdPl23e3ef8A6eou1madu9o7++x8W02pnqNZgx5Jfy/8VjaxwUYRTea3tFb7t7uz7PmgozSXRpt/mZ5rZZprgksu4MmvzyfNJwltSfK1XnsRPVZarZeq3f3MuK2LUFT2XSzy7r3mp6jVQxy627792j2x5Ek7X9zKmu3oY00Z01vYlFddr8zFzZFbX0s9JdLPPLjVr6kVq4z5dRp5x28Scscl5xkn+6T+h0SOTyfLqcNfgywaXq5cr+zZ1h2/Gv8AGuL5c1lKBgM6HIAAEQA0hIuICoCgA//Z",
				// messi
				"https://i2.wp.com/www.soccernation.com/wp-content/uploads/2016/08/breakfast-e1472147967165.jpeg?fit=634%2C515&ssl=1",
				// ronaldo
				"https://www.ronaldo7.net/faq/cristiano-ronaldo-eat.jpg");

		if (x == images.size()) {
			x = 0;
		}
		return images.get(x++);
	}

	public String randomLink() {
		List<String> links = Arrays.asList("http://www.mcdonalds.co.uk", "phone", "403", "/",
				"http://www.facebook.com");
		int choice = (int) Math.floor(links.size() * Math.random());
		return links.get(choice);
	}

	@Autowired
	private UserService service;

	static final String AB = "abcdefghijklmnopqrstuvwxyz1234567890";
	static SecureRandom rnd = new SecureRandom();

	String randomString( int len ){
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
	
	@RequestMapping("/")
	public String home(Model model) throws InterruptedException {
		Thread.sleep(500);
		int volume = (int)(Math.ceil(Math.random() * 32));
		String stringVolume = "";
		if(volume < 10) {
			stringVolume = "0" + volume;
		} else {
			stringVolume = String.valueOf(volume);
		}
		String link = "https://libraryofbabel.info/book.cgi?"
					+ randomString((int)(Math.ceil(Math.random() * 3600)))
					+ "-w"
					+ (int)(Math.ceil(Math.random() * 4))
					+ "-s"
					+ (int)(Math.ceil(Math.random() * 5))
					+ "-v"
					+ stringVolume
					+ ":"
					+ (int)(Math.ceil(Math.random() * 410));
		model.addAttribute("babel", link);
		model.addAttribute("homeImage", randomImage());
		model.addAttribute("myMessage", message());
		return "index";
	}

	@RequestMapping("/home")
	public String returnHome(Model model) {
		model.addAttribute("homeImage", randomImage());
		model.addAttribute("myMessage", message());
		model.addAttribute("seeMenu", 0);
		return "index";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		model.addAttribute("homeImage", randomImage());
		model.addAttribute("myMessage", message());
		return "index";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@PreAuthorize("isAnonymous()")
	public String register(ModelMap model, @ModelAttribute("userForm") @Valid User user, BindingResult result) {
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@PreAuthorize("isAnonymous()")
	public String registerUser(Model model, @ModelAttribute("userForm") @Valid User user, BindingResult result) {
		if (!result.hasErrors()) {
			List<User> users = new ArrayList<User>();
			users.add(user);
			service.saveUser(new User(user.getUsername(), user.getPassword(), true));
			model.addAttribute("homeImage", randomImage());
			model.addAttribute("myMessage", message());
			return "index";
		} else {
			return "register";
		}
	}

	@RequestMapping(value = "/logmeout", method = RequestMethod.GET)
	public String logout() {
		return "logoutpage";
	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String error403() {
		return "error403";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}

	@RequestMapping(value = "/myAccount", method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
	public String myAccount(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		User user = service.findByUsername(username);
		model.addAttribute("currentUser", user);
		return "myAccount";
	}

	@RequestMapping(value = "/contactPage", method = RequestMethod.GET)
	public String contactUs() {
		count = 0;
		return "contactPage";
	}

	@RequestMapping(value = "/findUs", method = RequestMethod.GET)
	public String findUs() {
		return "map";
	}

	@RequestMapping(value = "/phone", method = RequestMethod.GET)
	public String phone() {
		return "phone";
	}

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public String email(Model model) {
		String sending = "https://i.gifer.com/UWLV.gif";
		String sent = "data:image/png;base64,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";
		String titleSending = "Sending email...";
		String titleSent = "Your email has sent, thank you for contacting us";
		if (count == 0) {
			model.addAttribute("sending", sending);
			model.addAttribute("titleSending", titleSending);
			count++;
			return "email";
		}else if(count == 1){
			model.addAttribute("sending", sent);
			model.addAttribute("titleSending", titleSent);
			count++;
			return "email";
		}else {
			count++;
			return "index";
		}
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public String sendEmail(Model model, @ModelAttribute("emailForm") Item item, BindingResult result) {
		count = 0;
		String sending = "https://i.gifer.com/UWLV.gif";
		model.addAttribute("sending", sending);
		return "sendEmail";
	}

	int count = 0;

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String sendTheEmail(Model model, @ModelAttribute("emailForm") Item item, BindingResult result) {
		count = 0;
		String sending = "https://i.gifer.com/UWLV.gif";
		String titleSending = "Sending email...";
		model.addAttribute("sending", sending);
		model.addAttribute("titleSending", titleSending);
		return "email";
	}
}
