package com.iticbcn.pau_hernandez;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Dao.ClassificacioDAO;
import com.iticbcn.pau_hernandez.Dao.EquipDAO;
import com.iticbcn.pau_hernandez.Dao.JugadorDAO;
import com.iticbcn.pau_hernandez.Dao.LligaDAO;
import com.iticbcn.pau_hernandez.Model.Classificacio;
import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Jugador;
import com.iticbcn.pau_hernandez.Model.Lliga;

public class Main {

    public static void mostrarOpcions() {
        System.out.println("\n===== MENU PRINCIPAL =====");
        System.out.println("1. Gestionar Lliga");
        System.out.println("2. Gestionar Jugadors");
        System.out.println("3. Gestionar Classificació");
        System.out.println("4. Gestionar Equips");
        System.out.println("5. Sortir");
        System.out.print("Selecciona una opció: ");
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EquipDAO equipDAO = new EquipDAO(sessionFactory);
        LligaDAO lligaDAO = new LligaDAO(sessionFactory);
        ClassificacioDAO classificacioDAO = new ClassificacioDAO(sessionFactory);
        JugadorDAO jugadorDAO = new JugadorDAO(sessionFactory);
        Scanner scanner = new Scanner(System.in);

        boolean sortir = false;

        while (!sortir) {
            mostrarOpcions();
            int opcio = scanner.nextInt();
            scanner.nextLine();  

            switch (opcio) {
                case 1:
                    gestionarLliga(scanner, lligaDAO);
                    break;
                case 2:
                    gestionarJugador(scanner, jugadorDAO, equipDAO);
                    break;
                case 3:
                    gestionarClassificacio(scanner, classificacioDAO, equipDAO);
                    break;
                case 4:
                    gestionarEquip(scanner, equipDAO, lligaDAO);
                    break;
                case 5:
                    sortir = true;
                    System.out.println("Sortint de l'aplicació. Fins aviat!");
                    break;
                default:
                    System.out.println("Opció no vàlida.");
            }
        }

        scanner.close();
        sessionFactory.close();
    }

    // Submenú per gestionar Lliga
    public static void gestionarLliga(Scanner scanner, LligaDAO lligaDAO) {
        System.out.println("\n===== GESTIONAR LLIGA =====");
        System.out.println("1. Crear Lliga");
        System.out.println("2. Consultar Lliga per ID");
        System.out.println("3. Actualitzar Lliga per ID");
        System.out.println("4. Eliminar Lliga per ID");
        System.out.println("5. Llistar totes les Lligues");  
        System.out.print("Selecciona una opció: ");
        
        int opcio = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcio) {
            case 1:
                System.out.print("Nom de la lliga: ");
                String nomLliga = scanner.nextLine();
                System.out.print("Temporada: ");
                String temporada = scanner.nextLine();
                Lliga lliga = new Lliga();
                lliga.setNom_lliga(nomLliga);
                lliga.setTemporada(temporada);
                lligaDAO.crearLliga(lliga);
                System.out.println("Lliga creada amb èxit!");
                break;
    
            case 2:
                System.out.print("Introdueix l'ID de la lliga a consultar: ");
                int idConsulta = scanner.nextInt();
                scanner.nextLine();
                Lliga l = lligaDAO.obtenirLliga(idConsulta);
                if (l != null) {
                    System.out.println("🏆 " + l.getNom_lliga() + " - " + l.getTemporada());
                } else {
                    System.out.println("No s'ha trobat cap lliga amb aquest ID.");
                }
                break;
    
            case 3:
                System.out.print("Introdueix l'ID de la lliga a actualitzar: ");
                int idUpdate = scanner.nextInt();
                scanner.nextLine();
                Lliga lligaUpdate = lligaDAO.obtenirLliga(idUpdate);
                if (lligaUpdate != null) {
                    System.out.print("Nou nom de la lliga: ");
                    lligaUpdate.setNom_lliga(scanner.nextLine());
                    System.out.print("Nova temporada: ");
                    lligaUpdate.setTemporada(scanner.nextLine());
                    lligaDAO.actualitzarLliga(lligaUpdate);
                    System.out.println("Lliga actualitzada amb èxit!");
                } else {
                    System.out.println("No s'ha trobat cap lliga amb aquest ID.");
                }
                break;
    
            case 4:
                System.out.print("Introdueix l'ID de la lliga a eliminar: ");
                int idDelete = scanner.nextInt();
                scanner.nextLine();
                lligaDAO.eliminarLliga(idDelete);
                System.out.println("Lliga eliminada amb èxit!");
                break;
    
            case 5: 
                System.out.println("\n📜 Llistat de Lligues:");
                for (Lliga ll : lligaDAO.obtenirTotesLesLligues()) {
                    System.out.println("Id lliga: " + ll.getId_lliga() + "    Nom Lliga: " + ll.getNom_lliga() + "    Temporada: " + ll.getTemporada());
                }
                break;
    
            default:
                System.out.println("Opció no vàlida.");
        }
    }
        
    public static void gestionarJugador(Scanner scanner, JugadorDAO jugadorDAO, EquipDAO equipDAO) {
        System.out.println("\n===== GESTIONAR JUGADOR =====");
        System.out.println("1. Crear Jugador");
        System.out.println("2. Consultar Jugador per ID");
        System.out.println("3. Actualitzar Jugador per ID");
        System.out.println("4. Eliminar Jugador per ID");
        System.out.println("5. Llistar tots els Jugadors");  
        System.out.print("Selecciona una opció: ");
        int opcio = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcio) {
            case 1:
                System.out.print("Nom del jugador: ");
                String nomJugador = scanner.nextLine();
                System.out.print("Cognoms: ");
                String cognoms = scanner.nextLine();
    
                System.out.print("Introdueix l'ID de l'equip al qual pertany el jugador: ");
                int idEquip = scanner.nextInt();
                scanner.nextLine();
    
                Equip equip = equipDAO.obtenirEquip(idEquip);
                if (equip == null) {
                    System.out.println("No s'ha trobat cap equip amb aquest ID. No es pot crear el jugador.");
                    return;
                }
    
                Jugador jugador = new Jugador();
                jugador.setNom(nomJugador);
                jugador.setCognoms(cognoms);
                jugador.setEquip(equip); 
    
                jugadorDAO.crearJugador(jugador, idEquip);
                System.out.println("Jugador creat amb èxit!");
                break;
    
            case 2:
                System.out.print("Introdueix l'ID del jugador a consultar: ");
                int idConsulta = scanner.nextInt();
                scanner.nextLine();
                Jugador j = jugadorDAO.obtenirJugador(idConsulta);
                if (j != null) {
                    System.out.println(j.getNom() + " " + j.getCognoms());
                } else {
                    System.out.println("No s'ha trobat cap jugador amb aquest ID.");
                }
                break;
    
            case 3:
                System.out.print("Introdueix l'ID del jugador a actualitzar: ");
                int idUpdate = scanner.nextInt();
                scanner.nextLine();
                Jugador jugadorUpdate = jugadorDAO.obtenirJugador(idUpdate);
                if (jugadorUpdate != null) {
                    System.out.print("Nou nom del jugador: ");
                    jugadorUpdate.setNom(scanner.nextLine());
                    System.out.print("Noves cognoms del jugador: ");
                    jugadorUpdate.setCognoms(scanner.nextLine());
                    jugadorDAO.actualitzarJugador(jugadorUpdate);
                    System.out.println("Jugador actualitzat amb èxit!");
                } else {
                    System.out.println("No s'ha trobat cap jugador amb aquest ID.");
                }
                break;
    
            case 4:
                System.out.print("Introdueix l'ID del jugador a eliminar: ");
                int idDelete = scanner.nextInt();
                scanner.nextLine();
                jugadorDAO.eliminarJugador(idDelete);
                System.out.println("Jugador eliminat amb èxit!");
                break;
            
            case 5: 
                System.out.println("\nLlistat de Jugadors:");
                for (Jugador juga : jugadorDAO.obtenirTotsElsJugadors()) {
                    System.out.println("Id jugador: " + juga.getIdJugador() + "    Nom: " + juga.getNom() + "    Cognoms: " + juga.getCognoms() + "    Nom Equip: " + juga.getEquip().getNom_equip());
                }
                break;
    
            default:
                System.out.println("Opció no vàlida.");
                
        }
    }
    
    

    public static void gestionarClassificacio(Scanner scanner, ClassificacioDAO classificacioDAO, EquipDAO equipDAO) {
        System.out.println("\n===== GESTIONAR CLASSIFICACIÓ =====");
        System.out.println("1. Crear Classificació");
        System.out.println("2. Consultar Classificació per ID");
        System.out.println("3. Actualitzar Classificació per ID");
        System.out.println("4. Eliminar Classificació per ID");
        System.out.println("5. Llistar totes les Classificacions");  
        System.out.print("Selecciona una opció: ");
        int opcio = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcio) {
            case 1:
                System.out.print("Introdueix els punts: ");
                int punts = scanner.nextInt();
                System.out.print("Introdueix partits jugats: ");
                int partits = scanner.nextInt();
                System.out.print("Introdueix victòries: ");
                int victories = scanner.nextInt();
                scanner.nextLine();
    
                System.out.print("Introdueix l'ID de l'equip per associar la classificació: ");
                int idEquip = scanner.nextInt();
                scanner.nextLine();
    
                Equip equip = equipDAO.obtenirEquip(idEquip);
                if (equip == null) {
                    System.out.println("No s'ha trobat cap equip amb aquest ID. No es pot crear la classificació.");
                    return; 
                }
    
                Classificacio classificacio = new Classificacio();
                classificacio.setPunts(punts);
                classificacio.setPartits_jugats(partits);
                classificacio.setVictories(victories);
                classificacio.setEquip(equip); 

                classificacioDAO.crearClassificacio(classificacio);
                System.out.println("Classificació creada amb èxit!");
                break;
    
            case 2:
                System.out.print("Introdueix l'ID de la classificació a consultar: ");
                Long idConsulta = scanner.nextLong();
                scanner.nextLine();
                Classificacio c = classificacioDAO.obtenirClassificacio(idConsulta);
                if (c != null) {
                    System.out.println("Punts: " + c.getPunts() + " | Partits jugats: " + c.getPartits_jugats() + " | Victòries: " + c.getVictories());
                } else {
                    System.out.println("No s'ha trobat cap classificació amb aquest ID.");
                }
                break;
    
            case 3:
                System.out.print("Introdueix l'ID de la classificació a actualitzar: ");
                Long idUpdate = scanner.nextLong();
                scanner.nextLine();
                Classificacio classificacioUpdate = classificacioDAO.obtenirClassificacio(idUpdate);
                if (classificacioUpdate != null) {
                    System.out.print("Nous punts: ");
                    classificacioUpdate.setPunts(scanner.nextInt());
                    System.out.print("Noves partits jugats: ");
                    classificacioUpdate.setPartits_jugats(scanner.nextInt());
                    System.out.print("Noves victòries: ");
                    classificacioUpdate.setVictories(scanner.nextInt());
                    scanner.nextLine();
    
                    classificacioDAO.actualitzarClassificacio(classificacioUpdate);
                    System.out.println("Classificació actualitzada amb èxit!");
                } else {
                    System.out.println("No s'ha trobat cap classificació amb aquest ID.");
                }
                break;
    
            case 4:
                System.out.print("Introdueix l'ID de la classificació a eliminar: ");
                Long idDelete = scanner.nextLong();
                scanner.nextLine();
                classificacioDAO.eliminarClassificacio(idDelete);
                System.out.println("Classificació eliminada amb èxit!");
                break;

            case 5:
            System.out.println("\nLlistat de Classificacions:");
            for (Classificacio clas : classificacioDAO.obtenirTotesLesClassificacions()) {
                System.out.println("Id classificacio: " + clas.getId_classificacio() + "    Nom Equip: " + clas.getEquip().getNom_equip() + "    Punts: " + clas.getPunts() + "    Partits jugats: " + clas.getPartits_jugats() + "    Victories: " + clas.getVictories() + ")");
            }
            break;

            default:
            System.out.println("Opció no vàlida.");
            
        }
    }
    
    

    public static void gestionarEquip(Scanner scanner, EquipDAO equipDAO, LligaDAO lligaDAO) {
        System.out.println("\n===== GESTIONAR EQUIP =====");
        System.out.println("1. Crear Equip");
        System.out.println("2. Consultar Equip per ID");
        System.out.println("3. Actualitzar Equip per ID");
        System.out.println("4. Eliminar Equip per ID");
        System.out.println("5. Llistar tots els Equips");  
        System.out.print("Selecciona una opció: ");
        int opcio = scanner.nextInt();
        scanner.nextLine();
    
        switch (opcio) {
            case 1:
                System.out.print("Nom de l'equip: ");
                String nomEquip = scanner.nextLine();
                System.out.print("Ciutat de l'equip: ");
                String ciutat = scanner.nextLine();
                
                System.out.print("Introdueix l'ID de la lliga a la qual pertany l'equip: ");
                int idLliga = scanner.nextInt();
                scanner.nextLine();
                
                Lliga lliga = lligaDAO.obtenirLliga(idLliga);
                if (lliga == null) {
                    System.out.println("No s'ha trobat cap lliga amb aquest ID. No es pot crear l'equip.");
                    return;
                }
                
                Equip equip = new Equip();
                equip.setNom_equip(nomEquip);
                equip.setCiutat(ciutat);
                equip.setLliga(lliga); 
                
                equipDAO.crearEquip(equip, idLliga);
                System.out.println("Equip creat amb èxit!");
                break;
    
            case 2:
                System.out.print("Introdueix l'ID de l'equip a consultar: ");
                int idConsulta = scanner.nextInt();
                scanner.nextLine();
                Equip e = equipDAO.obtenirEquip(idConsulta);
                if (e != null) {
                    System.out.println(e.getNom_equip() + " - " + e.getCiutat() + " (Lliga: " + e.getLliga().getNom_lliga() + ")");
                } else {
                    System.out.println("No s'ha trobat cap equip amb aquest ID.");
                }
                break;
    
            case 3:
                System.out.print("Introdueix l'ID de l'equip a actualitzar: ");
                int idUpdate = scanner.nextInt();
                scanner.nextLine();
                Equip equipUpdate = equipDAO.obtenirEquip(idUpdate);
                if (equipUpdate != null) {
                    System.out.print("Nou nom de l'equip: ");
                    equipUpdate.setNom_equip(scanner.nextLine());
                    System.out.print("Nova ciutat de l'equip: ");
                    equipUpdate.setCiutat(scanner.nextLine());
    
                    equipDAO.actualitzarEquip(equipUpdate);
                    System.out.println("Equip actualitzat amb èxit!");
                } else {
                    System.out.println("No s'ha trobat cap equip amb aquest ID.");
                }
                break;
    
            case 4:
                System.out.print("Introdueix l'ID de l'equip a eliminar: ");
                int idDelete = scanner.nextInt();
                scanner.nextLine();
                equipDAO.eliminarEquip(idDelete);
                System.out.println("Equip eliminat amb èxit!");
                break;
            
            case 5:
            System.out.println("\nLlistat d'Equips:");
            for (Equip equipo : equipDAO.obtenirTotsElsEquips()) {
                System.out.println("Id equip: " + equipo.getId_equip() + "    Nom Equip: " + equipo.getNom_equip() + "    Ciutat: " + equipo.getCiutat());
            }
            break;
            
        }
    }   
}
