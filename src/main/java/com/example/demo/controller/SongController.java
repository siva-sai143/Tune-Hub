package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Song;
import com.example.demo.services.SongService;

@Controller
public class SongController {
	@Autowired
	SongService service;
	@PostMapping("/addsong")
	public String addSong(@ModelAttribute Song song) {
		boolean song_status=service.songExist(song.getName());
		if(song_status==false) {
		     service.addSong(song);
		}
		else{
			System.out.println("song alreaady exist..!");
		}
		
		
		return "adminHome";
		
	}
	@GetMapping("/viewsong")
    public String viewSong(Model model) {
		
		List<Song>songList=service.fetchAllSongs();
		model.addAttribute("songs",songList);
	
		return "displaySongs";
		
	}
	@GetMapping("/playsong")
    public String playSong(Model model) {
		
		boolean premiumUser=true;
		if(premiumUser) {
		
		List<Song>songList=service.fetchAllSongs();
		model.addAttribute("songs",songList);
	
		return "displaySongs";
		
	    }
		else {
			return "makePayment";
		}
	}

}
