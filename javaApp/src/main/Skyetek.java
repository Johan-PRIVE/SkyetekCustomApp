package main;

/*import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.io.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;
import java.util.Hashtable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.util.ArrayList;*/
import java.lang.Thread;


public class Skyetek implements Runnable {//sounds
	Thread MyThread;
	 private String Threadname;
	 Skyetek(String name) {
		 Threadname = name;
	 }
	 
	 @Override
	public void run() {
		System.out.println("Thread running : " + Threadname);
		try {
			while(true) 
			{
				Generalist.example.specialReader();
				Thread.sleep(1);
			}
			
		} catch (InterruptedException e) {
		    System.out.println("Thread has been interrupted");
		}
	}

	public void start() {
		System.out.println("Thread started : " + Threadname);
		if (MyThread == null) {
			MyThread = new Thread(this, Threadname);
			MyThread.start();
		}	 
	}
}
	 



