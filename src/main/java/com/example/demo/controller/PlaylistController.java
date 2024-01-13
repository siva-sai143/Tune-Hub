package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Playlist;
import com.example.demo.entity.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;

@Controller
public class PlaylistController {
	@Autowired
	SongService service;
	@Autowired
	PlaylistService playlistService;
	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		List<Song> songList =service.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "createPlaylist";
		
	}
	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		playlistService.addPlaylist(playlist);
		List<Song> songlist =playlist.getSongs();
		for(Song s:songlist) {
			s.getPlaylists().add(playlist);
			service.updateSong(s);
		}
		return "adminHome";
	}
	@GetMapping("/viewplaylist")
	public String viewPlaylist(Model model) {
		List<Playlist> allPlaylist = playlistService.fetchplaylist();
		
		model.addAttribute("allPlaylist",allPlaylist);
		
		return "displayPlaylist";
		
		
		
	}

}
