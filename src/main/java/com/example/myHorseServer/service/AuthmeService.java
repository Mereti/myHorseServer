package com.example.myHorseServer.service;

import com.example.myHorseServer.dto.gamer.GamerRegisterDto;
import com.example.myHorseServer.model.Authme;
import com.example.myHorseServer.repository.AuthmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

@Service
public class AuthmeService {
    @Autowired
    AuthmeRepository authmeRepository;

    @Autowired
    AuthmePasswordEncoder authmePasswordEncoder;

    public Authme createAuthmeAccount(GamerRegisterDto gamer){

            String ip = null;
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
             ip = socket.getLocalAddress().getHostAddress();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        }
        System.out.println("passssssworrddd: " + gamer.getPassword());

        String password = authmePasswordEncoder.encode(gamer.getPassword());

        System.out.println("passssssworrddd: " + password);

        Authme creator = new Authme();
        creator.setUsername(gamer.getNickname());
        creator.setRealname(gamer.getNickname());
        creator.setPassword(password);
        creator.setIp(ip);
        creator.setLastLogin((int) new Date().getTime());
        creator.setX(0.0);
        creator.setY(0.0);
        creator.setZ(0.0);
        creator.setWorld("world");
        creator.setRegdate((int) new Date().getTime());
        creator.setRegip(ip);
        creator.setYaw(null);
        creator.setPitch(null);
        creator.setEmail(gamer.getEmail());
        creator.setGamerLogged(false);
        creator.setHasSession(false);
        creator.setTotp(null);

        creator = authmeRepository.save(creator);
        return new Authme(
                creator.getId(),
                creator.getUsername(),
                creator.getRealname(),
                creator.getPassword(),
                creator.getIp(),
                null,
                creator.getX(),
                creator.getY(),
                creator.getZ(),
                creator.getWorld(),
                creator.getRegdate(),
                creator.getRegip(),
                creator.getYaw(),
                creator.getPitch(),
                creator.getEmail(),
                false,
                false,
                creator.getTotp()
        );
    }
}
