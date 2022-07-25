package main;
import main.db.Mage;
import main.db.Tower;


import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static boolean running = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em;

        while(running) {
            input = scanner.next();
            switch (input){
                case "q":
                    running = false;
                    break;
                case "ma":
                    System.out.println("enter mage name");
                    String mName = scanner.next();
                    System.out.println("enter mage level");
                    int level = scanner.nextInt();
                    System.out.println("enter mage tower");
                    String tower = scanner.next();

                    em = emf.createEntityManager();
                    Tower towerFind = em.find(Tower.class, tower);
                    em.close();
                    if (towerFind == null){
                        System.out.println("Tower " + tower + " not exist");
                        break;
                    }
                    em = emf.createEntityManager();
                    Mage mageFound = em.find(Mage.class, mName);
                    em.close();
                    if (mageFound != null){
                        System.out.println("mage " + mName + " already exist");
                        break;
                    }

                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    Mage mageToAdd = new Mage(mName, level);

                    //update list in tower to witch new mage was added
                    String queryString = "SELECT t FROM Tower t WHERE t.name LIKE :name";
                    Query query = em.createQuery(queryString, Tower.class);
                    query.setParameter("name", tower);
                    Tower foundTower = (Tower)query.getSingleResult();
                    List<Mage> magesList = foundTower.getMages();
                    magesList.add(mageToAdd);
                    mageToAdd.setTower(foundTower);
                    em.persist(mageToAdd);
                    em.getTransaction().commit();
                    em.close();
                    break;

                case "ta":
                    System.out.println("enter tower name");
                    String tName = scanner.next();
                    System.out.println("enter tower height");
                    int height = scanner.nextInt();

                    em = emf.createEntityManager();
                    Tower towerFound = em.find(Tower.class, tName);
                    em.close();
                    if (towerFound != null){
                        System.out.println("tower " + tName + " already exist");
                        break;
                    }

                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    em.persist(new Tower(tName, height));
                    em.getTransaction().commit();
                    em.close();
                    break;

                case "md":
                    System.out.println("enter mage name to be deleted");
                    String mNameD = scanner.next();

                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    queryString = "SELECT m FROM Mage m WHERE m.name LIKE :name";
                    query = em.createQuery(queryString, Mage.class);
                    query.setParameter("name", mNameD);
                    try {
                        Mage foundMage = (Mage) query.getSingleResult();
                        queryString = "DELETE FROM Mage m WHERE m.name LIKE :name";
                        query = em.createQuery(queryString);
                        query.setParameter("name", mNameD);
                        query.executeUpdate();
                    } catch (NoResultException ex) {
                        System.out.println("mage does not exist");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }

                    em.getTransaction().commit();
                    em.close();
                    break;

                case "td":
                    System.out.println("enter tower name to be deleted");
                    String tNameD = scanner.next();

                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    queryString = "SELECT t FROM Tower t WHERE t.name LIKE :name";
                    query = em.createQuery(queryString, Tower.class);
                    query.setParameter("name", tNameD);
                    try {
                        Tower foundTowerD = (Tower) query.getSingleResult();
                        em.remove(foundTowerD);
                    } catch (NoResultException ex){
                        System.out.println("tower does not exist");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }
                    em.getTransaction().commit();
                    em.close();
                    break;

                case "ts":
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    queryString = "SELECT t FROM Tower t";
                    query = em.createQuery(queryString, Tower.class);
                    List<Tower> foundTowersS = query.getResultList();
                    if (foundTowersS.size() == 0){
                        System.out.println("no tower saved");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }
                    for (Tower towerToShow : foundTowersS){
                        towerToShow.print();
                    }
                    em.getTransaction().commit();
                    em.close();
                    break;

                case "ms":
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    queryString = "SELECT m FROM Mage m";
                    query = em.createQuery(queryString, Mage.class);
                    List<Mage> foundMagesS = query.getResultList();
                    if (foundMagesS.size() == 0){
                        System.out.println("no mage saved");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }
                    for (Mage mageToShow : foundMagesS){
                        mageToShow.print();
                    }
                    em.getTransaction().commit();
                    em.close();
                    break;

                case "msl":
                    System.out.println("enter mage level to be showed");
                    int mageLevel = scanner.nextInt();
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    String queryString2 = "SELECT m FROM Mage m WHERE m.level > :level";
                    Query query2 = em.createQuery(queryString2, Mage.class);
                    query2.setParameter("level", mageLevel);
                    List<Mage> foundMagesL = query2.getResultList();
                    if (foundMagesL.size() == 0){
                        System.out.println("no mage found");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }
                    for (Mage mageToShow : foundMagesL){
                        mageToShow.print();
                    }
                    em.getTransaction().commit();
                    em.close();
                    break;
                case "tsh":
                    System.out.println("enter tower height to be showed");
                    int towerHeight = scanner.nextInt();
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    String queryString3 = "SELECT t FROM Tower t WHERE t.height < :height";
                    Query query3 = em.createQuery(queryString3, Tower.class);
                    query3.setParameter("height", towerHeight);
                    List<Tower> foundTowersH = query3.getResultList();
                    if (foundTowersH.size() == 0){
                        System.out.println("no tower found");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }
                    for (Tower towerToShow : foundTowersH){
                        towerToShow.print();
                    }
                    em.getTransaction().commit();
                    em.close();
                    break;

                case "mstl":
                    System.out.println("enter tower name to be chosen");
                    String towerChosen = scanner.next();
                    em = emf.createEntityManager();
                    em.getTransaction().begin();
                    String queryString4 = "SELECT t FROM Tower t WHERE t.name LIKE :name";
                    Query query4 = em.createQuery(queryString4, Tower.class);
                    query4.setParameter("name", towerChosen);
                    try {Tower foundTower2 = (Tower) query4.getSingleResult();
                        System.out.println("enter mage level to be chosen");
                        int mageLevel2 = scanner.nextInt();
                        List<Mage> magesFromTower = foundTower2.getMages();
                        for (Mage mageToShow : magesFromTower){
                            if (mageToShow.getLevel() > mageLevel2){
                                mageToShow.print();
                            }
                        }
                    } catch (NoResultException exception) {
                        System.out.println("no tower found");
                        em.getTransaction().rollback();
                        em.close();
                        break;
                    }

                    em.getTransaction().commit();
                    em.close();
                    break;
                default:
                    break;
            }
        }
        emf.close();
        scanner.close();
    }
}
