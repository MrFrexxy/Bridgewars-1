package bridgewars.utils;

import bridgewars.Main;
import bridgewars.items.*;
import bridgewars.items.Error;
import org.bukkit.Bukkit;

import java.io.*;
import java.util.*;

public class ItemManager {
    private static File config;
    private static ArrayList<ICustomItem> allItems;
    private static ItemPool activeItems;
    public ItemManager(Main plugin){
        config = new File("./plugins/bridgewars/itempool.ini");

        allItems = new ArrayList<>();
        allItems.add(new ForceFieldGenerator(plugin));
        allItems.add(new BlackHole(plugin));
        allItems.add(new BridgeEgg(plugin));
        allItems.add(new LifeforcePotion());
        allItems.add(new PortableDoinkHut(plugin));
        allItems.add(new Fireball(plugin));
        allItems.add(new HomeRunBat(plugin));
        allItems.add(new SadRoom(plugin));
        allItems.add(new Axe());
        allItems.add(new BasicBoots());
        allItems.add(new BasicChestplate());
        allItems.add(new BasicHelmet());
        allItems.add(new BasicLeggings());
        allItems.add(new BasicSword());
        allItems.add(new Bow());
        allItems.add(new GigaShears());
        allItems.add(new Shears());
        allItems.add(new WoolBlocks());
        allItems.sort(Comparator.comparing(ICustomItem::getRarity).thenComparing(o -> o.getClass().getSimpleName()));
        try {
            validateConfig();
        }catch (IOException ioe){
            Bukkit.getLogger().severe(ioe.getMessage());
        }
        generateItemPool(0.3f, 0.1f, 0);
    }
    private static class ItemPool{
        private final float greenPercent;
        private final float redPercent;
        private final float bluePercent;

        ArrayList<ICustomItem> blueItems;
        ArrayList<ICustomItem> redItems;
        ArrayList<ICustomItem> greenItems;
        ArrayList<ICustomItem> whiteItems;
        public ItemPool(float greenPercent, float redPercent, float bluePercent){
            this.greenPercent = greenPercent;
            this.redPercent = redPercent;
            this.bluePercent = bluePercent;

            whiteItems = new ArrayList<>();
            greenItems = new ArrayList<>();
            redItems = new ArrayList<>();
            blueItems = new ArrayList<>();
        }
        public void addItem(ICustomItem item){
            switch (item.getRarity()){
                case WHITE:
                    whiteItems.add(item);
                    break;
                case GREEN:
                    greenItems.add(item);
                    break;
                case RED:
                    redItems.add(item);
                    break;
                case BLUE:
                    blueItems.add(item);
            }
        }
        private ICustomItem getFromGroup(ArrayList<ICustomItem> itemGroup){
            if(itemGroup.size() <= 0) { return null; }
            return itemGroup.get((int)(Math.random()*itemGroup.size()));
        }
        public ICustomItem getRandomItem(){
            double val = Math.random();
            ICustomItem out;
            if(val < bluePercent){
                out = getFromGroup(blueItems);
            } else if(val < redPercent){
                out = getFromGroup(redItems);
            } else if(val < greenPercent){
                out = getFromGroup(greenItems);
            } else{
                out = getFromGroup(whiteItems);
            }
            return out;
        }
    }
    private static void validateConfig() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter(config));
        BufferedReader br = new BufferedReader(new FileReader(config));
        String[] names = br.lines().toArray(String[]::new);

        outerLoop:
        for (ICustomItem i : allItems)
        {
            String name = i.getClass().getSimpleName();
            for (String n : names
            ) {
                if(n.substring(1).equals(name)) {
                    continue outerLoop;
                }
            }
            //append this item if not found
            bw.write("1"+name);
            bw.newLine();
        }
        bw.close();
        br.close();
    }
    private static ItemPool getActivePool(float greenPercent, float redPercent, float bluePercent){
        ItemPool out = new ItemPool(greenPercent, redPercent, bluePercent);
        try{
            BufferedReader br = new BufferedReader(new FileReader(config));
            String line;
            nextLine:
            while ((line = br.readLine()) != null)
            {
                if(line.charAt(0) == '1'){
                    for(ICustomItem i : allItems){
                        if(i.getClass().getSimpleName().equals(line.substring(1))){
                            out.addItem(i);
                            continue nextLine;
                        }
                    }
                    Bukkit.getLogger().info("Adding item: " + line.substring(1));
                }
            }
        }catch (IOException ioe){
            Bukkit.getLogger().severe("Error while reading item pool config file!");
        }
        return out;
    }
    public static void generateItemPool(float greenPercent, float redPercent, float bluePercent){
        activeItems = getActivePool(greenPercent, redPercent, bluePercent);
    }
    public static ICustomItem getRandomItem(){
        return activeItems.getRandomItem();
    }
    public static ArrayList<String> getItemNames(){
        ArrayList<String> out = new ArrayList<>();
        for (ICustomItem allItem : allItems) {
            out.add(allItem.getClass().getSimpleName());
        }
        return out;
    }
    public static ICustomItem getItem(int index){
        return allItems.get(index);
    }
    public static ICustomItem getItem(String name){
        for (ICustomItem i : allItems
             ) {
            if(i.getClass().getSimpleName().equals(name)){
                return i;
            }
        }
        Bukkit.getLogger().severe("ERROR getting item: " + name);
        return new Error();
    }
}
