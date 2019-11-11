package com.example.greenroutine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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

        ArrayList<Card> plastic = new ArrayList<>();
        ArrayList<Card> paper = new ArrayList<>();
        ArrayList<Card> glass = new ArrayList<>();
        ArrayList<Card> metal = new ArrayList<>();
        ArrayList<Card> hhw= new ArrayList<>();
        ArrayList<Card> house= new ArrayList<>();
        ArrayList<Card> automotive= new ArrayList<>();
        ArrayList<Card> electronics= new ArrayList<>();
        ArrayList<Card> construction= new ArrayList<>();

        Card c1 = new Card(getDrawable(R.drawable.automotivefluids),"Automotive Fluids", "Transmission fluid, antifreeze, motor oil, etc.");
        Card c2 = new Card(getDrawable(R.drawable.carbatteries),"Car Batteries", "Batteries for automobiles");
        Card c3 = new Card(getDrawable(R.drawable.tires),"Tires", "Tires for automobiles");
        Card c4 = new Card(getDrawable(R.drawable.motoroilfilter),"Motor Oil and Filters", "Used motor oil and filters");
        automotive.add(c1);
        automotive.add(c2);
        automotive.add(c3);
        automotive.add(c4);

        Card c5 = new Card(getDrawable(R.drawable.carpet),"Carpet", "All varieties of carpet");
        Card c6 = new Card(getDrawable(R.drawable.constructionwaste),"Construction Waste", "Certain types of wood, brick and carpet");
        Card c7 = new Card(getDrawable(R.drawable.shingles),"Shingles", "Asphalt shingles");
        construction.add(c5);
        construction.add(c6);
        construction.add(c7);

        Card c8 = new Card(getDrawable(R.drawable.glassbottle),"Glass Bottles & Jars", "Glass containers");
        Card c9 = new Card(getDrawable(R.drawable.glasspanel),"Glass", "All other types of glass");
        glass.add(c8);
        glass.add(c9);

        Card c10 = new Card(getDrawable(R.drawable.packingpeanuts),"Packing Peanuts", "Polystyrene packaging materials");
        Card c11 = new Card(getDrawable(R.drawable.plasticbags),"Plastic Bags", "Both high and low density polyethylene bags");
        Card c12 = new Card(getDrawable(R.drawable.plasticcaplids),"Plastic Caps & Lids", "Plastic covers for bottles and jugs");
        Card c13 = new Card(getDrawable(R.drawable.plasticjugs),"Plastic Jugs & Bottles", "Jugs and bottles made from any type of plastic");
        Card c14 = new Card(getDrawable(R.drawable.plasticwrap),"Plastic Wrap & Film", "Film/wrap made from plastics");
        plastic.add(c10);
        plastic.add(c11);
        plastic.add(c12);
        plastic.add(c13);
        plastic.add(c14);

        Card c15 = new Card(getDrawable(R.drawable.cfl),"CFLs", "Compact fluorescent lightbulbs");
        Card c16 = new Card(getDrawable(R.drawable.mercury),"Items Containing Mercury", "Thermometers, batteries, etc.");
        Card c17 = new Card(getDrawable(R.drawable.medicalsharps),"Medical Sharps", "Needles, syringes, scalpels, etc.");
        Card c18 = new Card(getDrawable(R.drawable.paintcans),"Paint", "Oil or water based paint");
        Card c19 = new Card(getDrawable(R.drawable.pesticides),"Pesticides & Containers", "Chemicals used as pesticides and their containers");
        Card c20 = new Card(getDrawable(R.drawable.medicine),"Unwanted or Expired Medications", "Old/unused medicine");
        hhw.add(c15);
        hhw.add(c16);
        hhw.add(c17);
        hhw.add(c18);
        hhw.add(c19);
        hhw.add(c20);

        Card c21 = new Card(getDrawable(R.drawable.aerosolcans),"Aerosol Cans", "Spray cans which utilize aerosol");
        Card c22 = new Card(getDrawable(R.drawable.aluminum),"Aluminum Cans", "Cans made from aluminum");
        Card c23 = new Card(getDrawable(R.drawable.aluminumfoil),"Aluminum Foil", "Foil made from aluminum");
        Card c24 = new Card(getDrawable(R.drawable.tincans),"Tin or Steel Cans", "Cans made from tin or steel");
        Card c25 = new Card(getDrawable(R.drawable.metal),"Metal", "Scrap metal");
        metal.add(c21);
        metal.add(c22);
        metal.add(c23);
        metal.add(c24);
        metal.add(c25);

        Card c26 = new Card(getDrawable(R.drawable.cdstapes),"CDs & Tapes", "Media storage devices");
        Card c27 = new Card(getDrawable(R.drawable.cellphone),"Cell Phones", "Mobile phones");
        Card c28 = new Card(getDrawable(R.drawable.computermon),"Computer Monitors", "Displays for computers");
        Card c29 = new Card(getDrawable(R.drawable.computers),"Computers", "Laptops/desktops");
        Card c30 = new Card(getDrawable(R.drawable.inkcartridges),"Ink Cartridges", "Printer toner");
        Card c31 = new Card(getDrawable(R.drawable.largeappliances),"Large Appliances", "Fridges, stoves, dishwashers, etc.");
        Card c32 = new Card(getDrawable(R.drawable.largeelectronics),"Large Electronics", "Big-screen TVs, office copiers/printers, audio receivers, etc.");
        Card c33 = new Card(getDrawable(R.drawable.batteries),"Rechargeable Batteries", "Reusable batteries");
        Card c34 = new Card(getDrawable(R.drawable.singleusebatteries),"Single-Use Batteries", "Disposable batteries");
        Card c35 = new Card(getDrawable(R.drawable.smallappliances),"Small Appliances", "Blenders, toasters, etc.");
        Card c36 = new Card(getDrawable(R.drawable.smallelectronics),"Small Electronics", "MP3 players, digital cameras, etc.");
        electronics.add(c26);
        electronics.add(c27);
        electronics.add(c28);
        electronics.add(c29);
        electronics.add(c30);
        electronics.add(c31);
        electronics.add(c32);
        electronics.add(c33);
        electronics.add(c34);
        electronics.add(c35);
        electronics.add(c36);

        Card c37 = new Card(getDrawable(R.drawable.books),"Books & Magazines", "Hardcover and paperback books and magazines");
        Card c38 = new Card(getDrawable(R.drawable.cardboard),"Cardboard", "Boxes and other cardboard products");
        Card c39 = new Card(getDrawable(R.drawable.cartons),"Cartons", "Paper containers for liquids");
        Card c40 = new Card(getDrawable(R.drawable.giftcards),"Gift Cards & Gift Wrap", "Paper cards and wraps");
        Card c41 = new Card(getDrawable(R.drawable.newspaperhtr),"Newspaper", "Thin paper used to distribute news");
        Card c42 = new Card(getDrawable(R.drawable.paperbags),"Paper Bags", "Paper grocery bags, paper trash bags. etc.");
        Card c43 = new Card(getDrawable(R.drawable.papercups),"Paper Cups", "Paper bathroom cups, paper party cups, etc.");
        Card c44 = new Card(getDrawable(R.drawable.paperboard),"Paperboard", "Thicker paper products, ex: cereal boxes");
        Card c45 = new Card(getDrawable(R.drawable.phonebooks),"Phone Books", "Paper books containing contact information");
        Card c46 = new Card(getDrawable(R.drawable.shreddedpaper),"Shredded Paper", "Paper that has been run through a shredder");
        Card c47 = new Card(getDrawable(R.drawable.waxedcardboard),"Waxed Cardboard", "Corrugated cardboard lined with polyethylene");
        Card c48 = new Card(getDrawable(R.drawable.paper),"Paper", "Anything else considered to be paper");
        paper.add(c37);
        paper.add(c38);
        paper.add(c39);
        paper.add(c40);
        paper.add(c41);
        paper.add(c42);
        paper.add(c43);
        paper.add(c44);
        paper.add(c45);
        paper.add(c46);
        paper.add(c47);
        paper.add(c48);

        Card c49 = new Card(getDrawable(R.drawable.bathroomproducts),"Bathroom Products", "Shampoo bottles, toilet paper rolls, etc.");
        Card c50 = new Card(getDrawable(R.drawable.clothing),"Clothing & Accessories", "Old clothes and wearable products");
        Card c51 = new Card(getDrawable(R.drawable.cookingoil),"Cooking Oil", "Used cooking oil");
        Card c52 = new Card(getDrawable(R.drawable.cookware),"Cookware", "Pots, pans, spatulas, etc.");
        Card c53 = new Card(getDrawable(R.drawable.defaultimage),"Corks", "Products made from cork");
        Card c54 = new Card(getDrawable(R.drawable.fluorescenttubes),"Fluorescent Tubes", "Flourescent tubes used for lighting");
        Card c55 = new Card(getDrawable(R.drawable.frozenfoodboxes),"Frozen Food Boxes", "Paperboard coated in plastic");
        Card c56 = new Card(getDrawable(R.drawable.furniture),"Furniture", "Dressers, nightstands, chairs, etc.");
        Card c57 = new Card(getDrawable(R.drawable.householdcleaners),"Household Cleaners", "Glass cleaner, multi-use surface cleaner, etc.");
        Card c58 = new Card(getDrawable(R.drawable.mattress),"Mattresses", "Old mattresses");
        Card c59 = new Card(getDrawable(R.drawable.christmastrees),"Christmas Trees", "Cut trees utilized for the holidays");
        house.add(c49);
        house.add(c50);
        house.add(c51);
        house.add(c52);
        house.add(c53);
        house.add(c54);
        house.add(c55);
        house.add(c56);
        house.add(c57);
        house.add(c58);
        house.add(c59);


        String cat = getIntent().getStringExtra(CATEGORY_NAME);
        ItemListAdapterHTR mAdapter;
        switch(cat) {
            case ("Glass"):
                mAdapter = new ItemListAdapterHTR(this, glass, cat);
                break;
            case ("Plastic"):
                mAdapter = new ItemListAdapterHTR(this, plastic, cat);
                break;
            case ("Automotive"):
                mAdapter = new ItemListAdapterHTR(this, automotive, cat);
                break;
            case ("Metal"):
                mAdapter = new ItemListAdapterHTR(this, metal, cat);
                break;
            case ("Paper"):
                mAdapter = new ItemListAdapterHTR(this, paper, cat);
                break;
            case ("Construction"):
                mAdapter = new ItemListAdapterHTR(this, construction, cat);
                break;
            case ("Household Waste"):
                mAdapter = new ItemListAdapterHTR(this, hhw, cat);
                break;
            case ("Electronics"):
                mAdapter = new ItemListAdapterHTR(this, electronics, cat);
                break;
            case ("Household"):
                mAdapter = new ItemListAdapterHTR(this, house, cat);
                break;
            default:
                mAdapter = new ItemListAdapterHTR(this, plastic, cat);
                break;
        }

        recycleView.setAdapter(mAdapter);

    }
    /*
    public void sendToItemPage(View view){
        Intent itemIntent = new Intent(this, ItemPage.class);
        startActivity(itemIntent);
    }*/

}
