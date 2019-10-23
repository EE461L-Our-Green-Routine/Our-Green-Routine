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

        Card c1 = new Card(R.drawable.defaultimage,"Automotive Fluids", "Transmission fluid, antifreeze, motor oil, etc.");
        Card c2 = new Card(R.drawable.defaultimage,"Car Batteries", "Batteries for automobiles");
        Card c3 = new Card(R.drawable.defaultimage,"Tires", "Tires for automobiles");
        Card c4 = new Card(R.drawable.defaultimage,"Motor Oil and Filters", "Used motor oil and filters");
        automotive.add(c1);
        automotive.add(c2);
        automotive.add(c3);
        automotive.add(c4);

        Card c5 = new Card(R.drawable.defaultimage,"Carpet", "All varieties of carpet");
        Card c6 = new Card(R.drawable.defaultimage,"Construction Waste", "Certain types of wood, brick and carpet");
        Card c7 = new Card(R.drawable.defaultimage,"Shingles", "Asphalt shingles");
        construction.add(c5);
        construction.add(c6);
        construction.add(c7);

        Card c8 = new Card(R.drawable.defaultimage,"Glass Bottles & Jars", "Glass containers");
        Card c9 = new Card(R.drawable.defaultimage,"Glass", "All other types of glass");
        glass.add(c8);
        glass.add(c9);

        Card c10 = new Card(R.drawable.defaultimage,"Packing Peanuts", "Polystyrene packaging materials");
        Card c11 = new Card(R.drawable.defaultimage,"Plastic Bags", "Both high and low density polyethylene bags");
        Card c12 = new Card(R.drawable.defaultimage,"Plastic Caps & Lids", "Plastic covers for bottles and jugs");
        Card c13 = new Card(R.drawable.defaultimage,"Plastic Jugs & Bottles", "Jugs and bottles made from any type of plastic");
        Card c14 = new Card(R.drawable.defaultimage,"Plastic Wrap & Film", "Film/wrap made from plastics");
        plastic.add(c10);
        plastic.add(c11);
        plastic.add(c12);
        plastic.add(c13);
        plastic.add(c14);

        Card c15 = new Card(R.drawable.defaultimage,"CFLs", "Compact fluorescent lightbulbs");
        Card c16 = new Card(R.drawable.defaultimage,"Items Containing Mercury", "Thermometers, batteries, etc.");
        Card c17 = new Card(R.drawable.defaultimage,"Medical Sharps", "Needles, syringes, scalpels, etc.");
        Card c18 = new Card(R.drawable.defaultimage,"Paint", "Oil or water based paint");
        Card c19 = new Card(R.drawable.defaultimage,"Pesticides & Containers", "Chemicals used as pesticides and their containers");
        Card c20 = new Card(R.drawable.defaultimage,"Unwanted or Expired Medications", "Old/unused medicine");
        hhw.add(c15);
        hhw.add(c16);
        hhw.add(c17);
        hhw.add(c18);
        hhw.add(c19);
        hhw.add(c20);

        Card c21 = new Card(R.drawable.defaultimage,"Aerosol Cans", "Spray cans which utilize aerosol");
        Card c22 = new Card(R.drawable.defaultimage,"Aluminum Cans", "Cans made from aluminum");
        Card c23 = new Card(R.drawable.defaultimage,"Aluminum Foil", "Foil made from aluminum");
        Card c24 = new Card(R.drawable.defaultimage,"Tin or Steel Cans", "Cans made from tin or steel");
        Card c25 = new Card(R.drawable.defaultimage,"Metal", "Scrap metal");
        metal.add(c21);
        metal.add(c22);
        metal.add(c23);
        metal.add(c24);
        metal.add(c25);

        Card c26 = new Card(R.drawable.defaultimage,"CDs & Tapes", "Media storage devices");
        Card c27 = new Card(R.drawable.defaultimage,"Cell Phones", "Mobile phones");
        Card c28 = new Card(R.drawable.defaultimage,"Computer Monitors", "Displays for computers");
        Card c29 = new Card(R.drawable.defaultimage,"Computers", "Laptops/desktops");
        Card c30 = new Card(R.drawable.defaultimage,"Ink Cartridges", "Printer toner");
        Card c31 = new Card(R.drawable.defaultimage,"Large Appliances", "Fridges, stoves, dishwashers, etc.");
        Card c32 = new Card(R.drawable.defaultimage,"Large Electronics", "Big-screen TVs, office copiers/printers, audio receivers, etc.");
        Card c33 = new Card(R.drawable.defaultimage,"Rechargeable Batteries", "Reusable batteries");
        Card c34 = new Card(R.drawable.defaultimage,"Single-Use Batteries", "Disposable batteries");
        Card c35 = new Card(R.drawable.defaultimage,"Small Appliances", "Blenders, toasters, etc.");
        Card c36 = new Card(R.drawable.defaultimage,"Small Electronics", "MP3 players, digital cameras, etc.");
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

        Card c37 = new Card(R.drawable.defaultimage,"Books & Magazines", "Hardcover and paperback books and magazines");
        Card c38 = new Card(R.drawable.defaultimage,"Cardboard", "Boxes and other cardboard products");
        Card c39 = new Card(R.drawable.defaultimage,"Cartons", "Paper containers for liquids");
        Card c40 = new Card(R.drawable.defaultimage,"Gift Cards & Gift Wrap", "Paper cards and wraps");
        Card c41 = new Card(R.drawable.defaultimage,"Newspaper", "Thin paper used to distribute news");
        Card c42 = new Card(R.drawable.defaultimage,"Paper Bags", "Paper grocery bags, paper trash bags. etc.");
        Card c43 = new Card(R.drawable.defaultimage,"Paper Cups", "Paper bathroom cups, paper party cups, etc.");
        Card c44 = new Card(R.drawable.defaultimage,"Paperboard", "Thicker paper products, ex: cereal boxes");
        Card c45 = new Card(R.drawable.defaultimage,"Phone Books", "Paper books containing contact information");
        Card c46 = new Card(R.drawable.defaultimage,"Shredded Paper", "Paper that has been run through a shredder");
        Card c47 = new Card(R.drawable.defaultimage,"Waxed Cardboard", "Corrugated cardboard lined with polyethylene");
        Card c48 = new Card(R.drawable.defaultimage,"Paper", "Anything else considered to be paper");
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

        Card c49 = new Card(R.drawable.defaultimage,"Bathroom Products", "Shampoo bottles, toilet paper rolls, etc.");
        Card c50 = new Card(R.drawable.defaultimage,"Clothing & Accessories", "Old clothes and wearable products");
        Card c51 = new Card(R.drawable.defaultimage,"Cooking Oil", "Used cooking oil");
        Card c52 = new Card(R.drawable.defaultimage,"Cookware", "Pots, pans, spatulas, etc.");
        Card c53 = new Card(R.drawable.defaultimage,"Corks", "Products made from cork");
        Card c54 = new Card(R.drawable.defaultimage,"Fluorescent Tubes", "Flourescent tubes used for lighting");
        Card c55 = new Card(R.drawable.defaultimage,"Frozen Food Boxes", "Paperboard coated in plastic");
        Card c56 = new Card(R.drawable.defaultimage,"Furniture", "Dressers, nightstands, chairs, etc.");
        Card c57 = new Card(R.drawable.defaultimage,"Household Cleaners", "Glass cleaner, multi-use surface cleaner, etc.");
        Card c58 = new Card(R.drawable.defaultimage,"Mattresses", "Old mattresses");
        Card c59 = new Card(R.drawable.defaultimage,"Christmas Trees", "Cut trees utilized for the holidays");
        paper.add(c49);
        paper.add(c50);
        paper.add(c51);
        paper.add(c52);
        paper.add(c53);
        paper.add(c54);
        paper.add(c55);
        paper.add(c56);
        paper.add(c57);
        paper.add(c58);
        paper.add(c59);


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
