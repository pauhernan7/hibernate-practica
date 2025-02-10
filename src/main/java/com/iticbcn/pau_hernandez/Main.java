package com.iticbcn.pau_hernandez;

import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.iticbcn.pau_hernandez.Dao.ClassificacioDAO;
import com.iticbcn.pau_hernandez.Dao.EquipDAO;
import com.iticbcn.pau_hernandez.Dao.LligaDAO;
import com.iticbcn.pau_hernandez.Model.Equip;
import com.iticbcn.pau_hernandez.Model.Lliga;

public class Main {

    public static void mostrarOpcions() {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Crear Lliga");
        System.out.println("2. Mostrar totes les lligues");
        System.out.println("3. Crear Equip");
        System.out.println("4. Mostrar tots els equips");
        System.out.println("5. Crear Classificació");
        System.out.println("6. Mostrar totes les classificacions");
        System.out.println("7. Sortir");
        System.out.print("Selecciona una opció: ");
    }

    public static int obtenirOpcio(Scanner scanner) {
        while (true) {
            try {
                String entrada = scanner.nextLine().trim(); // Elimina espacios en blanco
                if (entrada.isEmpty()) { // Verifica si la entrada está vacía
                    System.out.println("Format erroni! No pots deixar-ho en blanc. Torna a intentar-ho.");
                    mostrarOpcions();
                    continue;
                }
                int opcio = Integer.parseInt(entrada); // Convierte la entrada en número
                if (opcio < 1 || opcio > 7) {
                    System.out.println("Opció no vàlida. Tria un número entre 1 i 7.");
                    mostrarOpcions();
                    continue;
                }
                return opcio; // Retorna la opción válida
            } catch (NumberFormatException e) {
                System.out.println("Format erroni! Introdueix un número del 1 al 7.");
                mostrarOpcions();
            }
        }
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        EquipDAO equipDAO = new EquipDAO(sessionFactory);
        LligaDAO lligaDAO = new LligaDAO(sessionFactory);
        ClassificacioDAO classificacioDAO = new ClassificacioDAO(sessionFactory);
        Scanner scanner = new Scanner(System.in);

        boolean sortir = false;

        while (!sortir) {
            mostrarOpcions();
            int opcio = obtenirOpcio(scanner); // Obtenemos la opción validada

            switch (opcio) {
                case 1:
                    System.out.print("Nom de la lliga: ");
                    String nomLliga = scanner.nextLine().trim();
                    System.out.print("Temporada: ");
                    String temporada = scanner.nextLine().trim();
                    if (nomLliga.isEmpty() || temporada.isEmpty()) {
                        System.out.println("Error: No pots deixar els camps buits.");
                        break;
                    }
                    Lliga lliga = new Lliga();
                    lliga.setNom_lliga(nomLliga);
                    lliga.setTemporada(temporada);
                    lligaDAO.crearLliga(lliga);
                    System.out.println("Lliga creada amb èxit!");
                    break;

                case 2:
                    List<Lliga> lligues = lligaDAO.obtenirTotesLesLligues();
                    if (lligues.isEmpty()) {
                        System.out.println("No hi ha lligues registrades.");
                    } else {
                        System.out.println("Llista de lligues:");
                        for (Lliga l : lligues) {
                            System.out.println(l.getNom_lliga() + " - " + l.getTemporada());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nom de l'equip: ");
                    String nomEquip = scanner.nextLine().trim();
                    System.out.print("Ciutat de l'equip: ");
                    String ciutat = scanner.nextLine().trim();
                    if (nomEquip.isEmpty() || ciutat.isEmpty()) {
                        System.out.println("Error: No pots deixar els camps buits.");
                        break;
                    }
                    Equip equip = new Equip();
                    equip.setNom_equip(nomEquip);
                    equip.setCiutat(ciutat);
                    equipDAO.crearEquip(equip);
                    System.out.println("Equip creat amb èxit!");
                    break;

                    //falta hacer casos 4,5,6 i arreglar creacion de equip

                case 7:
                    sortir = true;
                    System.out.println("Sortint de l'aplicació. Fins aviat!");
                    break;
            }

            // Preguntar si el usuario quiere seguir
            if (!sortir) {
                System.out.print("\nVols fer una altra operació? (s/n): ");
                String resposta = scanner.nextLine().trim().toLowerCase();
                while (!resposta.equals("s") && !resposta.equals("n")) {
                    System.out.print("Format erroni! Escriu 's' per sí o 'n' per no: ");
                    resposta = scanner.nextLine().trim().toLowerCase();
                }
                if (resposta.equals("n")) {
                    sortir = true;
                    System.out.println("Sortint de l'aplicació. Fins aviat!");
                }
            }
        }

        scanner.close();
        sessionFactory.close();
    }
}
