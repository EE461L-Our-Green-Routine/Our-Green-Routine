package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

/* Got information from https://developer.android.com/guide/topics/ui/layout/recyclerview#java */
public class ItemListHTR extends AppCompatActivity {
    private RecyclerView recycleView;
    private LinearLayoutManager layManager;
    private static final String CATEGORY_NAME = "CATEGORY_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list_htr);

        // use a linear layout manager
        layManager = new LinearLayoutManager(this);
        layManager.setOrientation(LinearLayoutManager.VERTICAL);

        recycleView = (RecyclerView) findViewById(R.id.my_recycler_view_items);
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(layManager);

        String cat = getIntent().getStringExtra(CATEGORY_NAME);
        ((TextView)findViewById(R.id.textView4)).setText(cat+":");
        ItemListAdapterHTR mAdapter;
        ArrayList<Card> display;
        switch(cat) {
            case ("Glass"):
                display = glassInit();
                break;
            case ("Plastic"):
                display = plasticInit();
                break;
            case ("Automotive"):
                display = autoInit();
                break;
            case ("Metal"):
                display = metalInit();
                break;
            case ("Paper"):
                display = paperInit();
                break;
            case ("Construction"):
                display = constructInit();
                break;
            case ("Household Waste"):
                display = hhwInit();
                break;
            case ("Electronics"):
                display = electronInit();
                break;
            case ("Household"):
                display = houseHoldInit();
                break;
            default:
                display = plasticInit();
                break;
        }
        mAdapter = new ItemListAdapterHTR(this, display);
        recycleView.setAdapter(mAdapter);

    }
    private ArrayList<Card> glassInit(){
        Card c1 = new Card(getDrawable(R.drawable.glassbottle),"Glass Bottles & Jars", "Glass containers");
        Card c2 = new Card(getDrawable(R.drawable.glasspanel),"Glass", "All other types of glass");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        return returnList;
    }

    private ArrayList<Card> plasticInit(){
        Card c1 = new Card(getDrawable(R.drawable.packingpeanuts),"Packing Peanuts", "Polystyrene packaging materials");
        Card c2 = new Card(getDrawable(R.drawable.plasticbags),"Plastic Bags", "Both high and low density polyethylene bags");
        Card c3 = new Card(getDrawable(R.drawable.plasticcaplids),"Plastic Caps & Lids", "Plastic covers for bottles and jugs");
        Card c4 = new Card(getDrawable(R.drawable.plasticjugs),"Plastic Jugs & Bottles", "Jugs and bottles made from any type of plastic");
        Card c5 = new Card(getDrawable(R.drawable.plasticwrap),"Plastic Wrap & Film", "Film/wrap made from plastics");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        returnList.add(c5);
        return returnList;
    }

    private ArrayList<Card> autoInit(){
        Card c1 = new Card(getDrawable(R.drawable.automotivefluids),"Automotive Fluids", "Transmission fluid, antifreeze, motor oil, etc.");
        Card c2 = new Card(getDrawable(R.drawable.carbatteries),"Car Batteries", "Batteries for automobiles");
        Card c3 = new Card(getDrawable(R.drawable.tires),"Tires", "Tires for automobiles");
        Card c4 = new Card(getDrawable(R.drawable.motoroilfilter),"Motor Oil and Filters", "Used motor oil and filters");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        return returnList;
    }

    private ArrayList<Card> metalInit(){
        Card c1 = new Card(getDrawable(R.drawable.aerosolcans),"Aerosol Cans", "Spray cans which utilize aerosol");
        Card c2 = new Card(getDrawable(R.drawable.aluminum),"Aluminum Cans", "Cans made from aluminum");
        Card c3 = new Card(getDrawable(R.drawable.aluminumfoil),"Aluminum Foil", "Foil made from aluminum");
        Card c4 = new Card(getDrawable(R.drawable.tincans),"Tin or Steel Cans", "Cans made from tin or steel");
        Card c5 = new Card(getDrawable(R.drawable.metal),"Metal", "Scrap metal");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        returnList.add(c5);
        return returnList;
    }

    private ArrayList<Card> paperInit() {
        Card c1 = new Card(getDrawable(R.drawable.books), "Books & Magazines", "Hardcover and paperback books and magazines");
        Card c2 = new Card(getDrawable(R.drawable.cardboard), "Cardboard", "Boxes and other cardboard products");
        Card c3 = new Card(getDrawable(R.drawable.cartons), "Cartons", "Paper containers for liquids");
        Card c4 = new Card(getDrawable(R.drawable.giftcards), "Gift Cards & Gift Wrap", "Paper cards and wraps");
        Card c5 = new Card(getDrawable(R.drawable.newspaperhtr), "Newspaper", "Thin paper used to distribute news");
        Card c6 = new Card(getDrawable(R.drawable.paperbags), "Paper Bags", "Paper grocery bags, paper trash bags. etc.");
        Card c7 = new Card(getDrawable(R.drawable.papercups), "Paper Cups", "Paper bathroom cups, paper party cups, etc.");
        Card c8 = new Card(getDrawable(R.drawable.paperboard), "Paperboard", "Thicker paper products, ex: cereal boxes");
        Card c9 = new Card(getDrawable(R.drawable.phonebooks), "Phone Books", "Paper books containing contact information");
        Card c10 = new Card(getDrawable(R.drawable.shreddedpaper), "Shredded Paper", "Paper that has been run through a shredder");
        Card c11 = new Card(getDrawable(R.drawable.waxedcardboard), "Waxed Cardboard", "Corrugated cardboard lined with polyethylene");
        Card c12 = new Card(getDrawable(R.drawable.paper), "Paper", "Anything else considered to be paper");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        returnList.add(c5);
        returnList.add(c6);
        returnList.add(c7);
        returnList.add(c8);
        returnList.add(c9);
        returnList.add(c10);
        returnList.add(c11);
        returnList.add(c12);
        return returnList;
    }

    private ArrayList<Card> constructInit(){
        Card c1 = new Card(getDrawable(R.drawable.carpet),"Carpet", "All varieties of carpet");
        Card c2 = new Card(getDrawable(R.drawable.constructionwaste),"Construction Waste", "Certain types of wood, brick and carpet");
        Card c3 = new Card(getDrawable(R.drawable.shingles),"Shingles", "Asphalt shingles");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        return returnList;
    }

    private ArrayList<Card> hhwInit() {
        Card c1 = new Card(getDrawable(R.drawable.cfl),"CFLs", "Compact fluorescent lightbulbs");
        Card c2 = new Card(getDrawable(R.drawable.mercury),"Items Containing Mercury", "Thermometers, batteries, etc.");
        Card c3 = new Card(getDrawable(R.drawable.medicalsharps),"Medical Sharps", "Needles, syringes, scalpels, etc.");
        Card c4 = new Card(getDrawable(R.drawable.paintcans),"Paint", "Oil or water based paint");
        Card c5 = new Card(getDrawable(R.drawable.pesticides),"Pesticides & Containers", "Chemicals used as pesticides and their containers");
        Card c6 = new Card(getDrawable(R.drawable.medicine),"Unwanted or Expired Medications", "Old/unused medicine");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        returnList.add(c5);
        returnList.add(c6);
        return returnList;
    }

    private ArrayList<Card> electronInit() {
        Card c1 = new Card(getDrawable(R.drawable.cdstapes),"CDs & Tapes", "Media storage devices");
        Card c2 = new Card(getDrawable(R.drawable.cellphone),"Cell Phones", "Mobile phones");
        Card c3 = new Card(getDrawable(R.drawable.computermon),"Computer Monitors", "Displays for computers");
        Card c4 = new Card(getDrawable(R.drawable.computers),"Computers", "Laptops/desktops");
        Card c5 = new Card(getDrawable(R.drawable.inkcartridges),"Ink Cartridges", "Printer toner");
        Card c6 = new Card(getDrawable(R.drawable.largeappliances),"Large Appliances", "Fridges, stoves, dishwashers, etc.");
        Card c7 = new Card(getDrawable(R.drawable.largeelectronics),"Large Electronics", "Big-screen TVs, office copiers/printers, audio receivers, etc.");
        Card c8 = new Card(getDrawable(R.drawable.batteries),"Rechargeable Batteries", "Reusable batteries");
        Card c9 = new Card(getDrawable(R.drawable.singleusebatteries),"Single-Use Batteries", "Disposable batteries");
        Card c10 = new Card(getDrawable(R.drawable.smallappliances),"Small Appliances", "Blenders, toasters, etc.");
        Card c11 = new Card(getDrawable(R.drawable.smallelectronics),"Small Electronics", "MP3 players, digital cameras, etc.");;
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        returnList.add(c5);
        returnList.add(c6);
        returnList.add(c7);
        returnList.add(c8);
        returnList.add(c9);
        returnList.add(c10);
        returnList.add(c11);
        return returnList;
    }

    private ArrayList<Card> houseHoldInit() {
        Card c1 = new Card(getDrawable(R.drawable.bathroomproducts),"Bathroom Products", "Shampoo bottles, toilet paper rolls, etc.");
        Card c2 = new Card(getDrawable(R.drawable.clothing),"Clothing & Accessories", "Old clothes and wearable products");
        Card c3 = new Card(getDrawable(R.drawable.cookingoil),"Cooking Oil", "Used cooking oil");
        Card c4 = new Card(getDrawable(R.drawable.cookware),"Cookware", "Pots, pans, spatulas, etc.");
        Card c5 = new Card(getDrawable(R.drawable.corks),"Corks", "Products made from cork");
        Card c6 = new Card(getDrawable(R.drawable.fluorescenttubes),"Fluorescent Tubes", "Flourescent tubes used for lighting");
        Card c7 = new Card(getDrawable(R.drawable.frozenfoodboxes),"Frozen Food Boxes", "Paperboard coated in plastic");
        Card c8 = new Card(getDrawable(R.drawable.furniture),"Furniture", "Dressers, nightstands, chairs, etc.");
        Card c9 = new Card(getDrawable(R.drawable.householdcleaners),"Household Cleaners", "Glass cleaner, multi-use surface cleaner, etc.");
        Card c10 = new Card(getDrawable(R.drawable.mattress),"Mattresses", "Old mattresses");
        Card c11 = new Card(getDrawable(R.drawable.christmastrees),"Christmas Trees", "Cut trees utilized for the holidays");
        ArrayList<Card> returnList = new ArrayList<>();
        returnList.add(c1);
        returnList.add(c2);
        returnList.add(c3);
        returnList.add(c4);
        returnList.add(c5);
        returnList.add(c6);
        returnList.add(c7);
        returnList.add(c8);
        returnList.add(c9);
        returnList.add(c10);
        returnList.add(c11);
        return returnList;
    }

}
