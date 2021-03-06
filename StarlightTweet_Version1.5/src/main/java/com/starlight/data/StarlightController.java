package com.starlight.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@ComponentScan("com.starlight.data")
@RestController
public class StarlightController {
	@Autowired
	TweetRepository tweetrepository;
	
	@Autowired
	AuthorRepository authorrepository;
	
	@RequestMapping("/test")
	public String test() {
		return "test";
	}
	@RequestMapping("/androidrequest")
	public List<Tweet> androidRequest(Long authorid){
		Author author = authorrepository.findById(authorid).get();
		List<Tweet> tweets = tweetrepository.findByAuthor(author);
		return tweets;
	}
	@RequestMapping("/withauthor")
	public ModelAndView withauthor(Long authorid, Model model){
		Author author = authorrepository.findById(authorid).get();
		List<Tweet> tweets = tweetrepository.findByAuthor(author);
		model.addAttribute("tweets", tweets);
		return new ModelAndView("withauthor");
	}
	
	@RequestMapping("/index")
	public ModelAndView index(Model model) {
		List<Tweet> tweets = tweetrepository.findAll();
		model.addAttribute("tweets", tweets);
		return new ModelAndView("index");
	}
	@PostMapping("/index")
	public ModelAndView searchcontent(Model model, @RequestParam("contentsearch") String search) {
		List<Tweet> tweets = tweetrepository.findByContentLike("%"+search+"%");
		model.addAttribute("tweets",tweets);
		return new ModelAndView("searchresult");
	}
}
