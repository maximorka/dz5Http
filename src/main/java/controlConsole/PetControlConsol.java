package controlConsole;

import lombok.RequiredArgsConstructor;
import pet.dao.PetDao;
import pet.entity.Category;
import pet.entity.Pet;
import pet.entity.StatusPets;
import pet.entity.Tag;

import java.util.*;

import static util.Menu.menuRequestForPet;

@RequiredArgsConstructor
public class PetControlConsol {
    public final Scanner scanner;
    private PetDao petDao = new PetDao();

    public void aboutPets() {

        System.out.println(menuRequestForPet);
        if (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            switch (s) {
                case "1":
                    System.out.println("uploadsAnImage() = " + uploadsAnImage());
                    break;
                case "2":
                    System.out.println("createNewPet() = " + createNewPet());
                    break;
                case "3":
                    System.out.println("updatePet() = " + updatePet());
                    break;
                case "4":
                    System.out.println("findPetsByStatus() = " + findPetsByStatus());
                    break;
                case "5":
                    System.out.println("getPetById() = " + getPetById());
                    break;
                case "6":
                    System.out.println("updateWithFormData() = " + updateWithFormData());
                    break;
                case "7":
                    System.out.println("deletePetById() = " + deletePetById());
                    break;
                case "8":
                    break;
                default:
                    System.out.println("Incorect, please try again ");
            }
        }
    }

    private String uploadsAnImage() {
        String responce = "";
        try {
            int id = Integer.parseInt(enterIdPet());
            String metadata = enterMetadata();
            String pathToFile = enterPathToFile();
            responce = petDao.uploadImage(id, metadata, pathToFile);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }
        return responce;
    }

    private String createNewPet() {
        String responce = "";
        try {
            int id = Integer.parseInt(enterIdPet());
            Category category = enterCategory();
            String name = enterName();
            String[] photoUrls = enterPhotoUrls();
            Tag[] tags = enterTags();
            StatusPets status = enterStatus();

            responce = petDao.addPet(Pet.builder().id(id).category(category).name(name).photoUrls(photoUrls).tags(tags).status(status).build());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }
        return responce;
    }

    private String updatePet() {
        String responce = "";
        try {
            int id = Integer.parseInt(enterIdPet());
            Category category = enterCategory();
            String name = enterName();
            String[] photoUrls = enterPhotoUrls();
            Tag[] tags = enterTags();
            StatusPets status = enterStatus();

            responce = petDao.updatePet(Pet.builder().id(id).category(category).name(name).photoUrls(photoUrls).tags(tags).status(status).build());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }
        return responce;
    }

    private String findPetsByStatus() {
        System.out.println("Enter status for seach:");
        String responce = "";
        try {
            StatusPets status = null;
            if (scanner.hasNextLine()) {
                status = StatusPets.valueOf(scanner.nextLine());
            }
            responce = petDao.findByStatus(status.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }
        return responce;
    }

    private String getPetById() {
        String responce = "";
        try {
            responce = petDao.getPetById(Integer.parseInt(enterIdPet()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }
        return responce;
    }

    private String updateWithFormData() {
        String responce = "";
        try {
            int id = Integer.parseInt(enterIdPet());
            String name = enterName();
            StatusPets status = enterStatus();

            responce = petDao.updatePetWithFormData(id, name, status.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }
        return responce;
    }

    private String deletePetById() {
        String responce = "";
        try {
            int id = Integer.parseInt(enterIdPet());
            String apiKey = enterApiKey();
            if (apiKey.equals("")) {
                responce = petDao.deletePetById(id);
            } else {
                responce = petDao.deletePetById(id, apiKey);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Incorrect");
            aboutPets();
        }

        return responce;


    }

    private String enterMetadata() {
        System.out.println("Enter metaData:");
        String metaData = "";
        if (scanner.hasNextLine()) {
            metaData = scanner.nextLine();
        }
        return metaData;

    }

    private String enterPathToFile() {
        System.out.println("Enter path to image:");
        String pathToImage = "";
        if (scanner.hasNextLine()) {
            pathToImage = scanner.nextLine();
        }
        return pathToImage;
    }

    private String enterApiKey() {
        System.out.println("Enter api key:");
        String apiKey = "";
        if (scanner.hasNextLine()) {
            apiKey = scanner.nextLine();
        }
        return apiKey;
    }

    List<String> photoUrls = new ArrayList<>();

    private String[] enterPhotoUrls() {
        System.out.println("Enter photo Urls:");
        if (scanner.hasNextLine()) {
            photoUrls.add(scanner.nextLine());
        }
        System.out.println("add next url? (Y,N)");
        if (scanner.hasNextLine()) {
            if (scanner.nextLine().equals("Y")) {
                enterPhotoUrls();
            } else {
                String[] urls = new String[photoUrls.size()];
                for (int i = 0; i < urls.length; i++) {
                    urls[i] = photoUrls.get(i);
                }
                return urls;
            }
        }
        return null;
    }

    private String enterName() {
        System.out.println("Enter name:");
        String name = "";

        if (scanner.hasNextLine()) {
            name = scanner.nextLine();
        }
        return name;
    }

    private Category enterCategory() {
        System.out.println("Enter category(id,nameCategory):");
        String idCategory = "";
        String nameCategory = "";

        if (scanner.hasNextLine()) {
            String tmp = scanner.nextLine();
            int indexDelimiter = 0;
            indexDelimiter = tmp.indexOf(",");
            idCategory = tmp.substring(0, indexDelimiter);
            nameCategory = tmp.substring(indexDelimiter + 1, tmp.length());
        }
        return Category.builder()
                .id(Integer.parseInt(idCategory))
                .name(nameCategory)
                .build();
    }

    private String enterIdPet() {
        System.out.println("Enter id:");
        String id = "";

        if (scanner.hasNextLine()) {
            id = scanner.nextLine();
        }
        if (id.matches("\\d+"))
            System.out.println("Ok");
        else {
            System.out.println("Incorrect id");
            id = "";
        }
        return id;
    }

    private StatusPets enterStatus() {
        System.out.println("Enter statusPet (available, pending, sold):");
        if (scanner.hasNextLine()) {
            return StatusPets.valueOf(scanner.nextLine());
        } else
            return null;
    }


    List<Tag> tagList = new LinkedList<>();

    private Tag[] enterTags() {
        System.out.println("Enter tags(id,name):");
        String idTags;
        String nameTags;

        if (scanner.hasNextLine()) {
            String tmp = scanner.nextLine();
            int indexDelimiter = 0;
            indexDelimiter = tmp.indexOf(",");
            idTags = tmp.substring(0, indexDelimiter);
            nameTags = tmp.substring(indexDelimiter + 1, tmp.length());
            tagList.add(Tag.builder().id(Integer.parseInt(idTags)).name(nameTags).build());
        }

        System.out.println("add next tag? (Y,N)");
        if (scanner.hasNextLine()) {
            if (scanner.nextLine().equals("Y")) {
                enterPhotoUrls();
            } else {
                Tag[] tags = new Tag[photoUrls.size()];
                for (int i = 0; i < tags.length; i++) {
                    tags[i] = tagList.get(i);
                }
                return tags;
            }
        }
        return null;
    }
}
