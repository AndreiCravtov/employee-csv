package com.sparta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        String data = """
                198429,Mrs.,Serafina,I,Bumgarner,F,serafina.bumgarner@exxonmobil.com,9/21/1982,2/1/2008,69294
                178566,Mrs.,Juliette,M,Rojo,F,juliette.rojo@yahoo.co.uk,5/8/1967,6/4/2011,193912
                647173,Mr.,Milan,F,Krawczyk,M,milan.krawczyk@hotmail.com,4/4/1980,1/19/2012,123681
                847634,Mr.,Elmer,R,Jason,M,elmer.jason@yahoo.com,4/9/1996,5/28/2017,93504
                260736,Ms.,Zelda,P,Forest,F,zelda.forest@ibm.com,11/27/1959,1/28/2014,176642
                811306,Mr.,Rhett,P,Wan,M,rhett.wan@hotmail.com,7/14/1976,1/21/2009,59406
                956633,Mr.,Hal,H,Farrow,M,hal.farrow@cox.net,3/15/1967,2/25/1991,164580
                629539,Dr.,Del,I,Fernandez,M,del.fernandez@hotmail.com,8/13/1991,4/7/2016,138662
                784160,Dr.,Corey,A,Jackman,M,corey.jackman@gmail.com,4/12/1959,6/29/1984,57616
                744723,Hon.,Bibi,H,Paddock,F,bibi.paddock@yahoo.co.in,10/20/1991,11/2/2016,87148
                423093,Mr.,Eric,O,Manning,M,eric.manning@yahoo.com,11/2/1980,10/28/2002,149363
                207808,Ms.,Renetta,T,Hafner,F,renetta.hafner@aol.com,1/29/1975,8/22/1998,180289
                338634,Ms.,Paz,T,Pearman,F,paz.pearman@gmail.com,2/28/1960,5/25/1982,144804
                324573,Hon.,Ardath,Q,Forman,F,ardath.forman@gmail.com,11/12/1982,10/16/2009,189373
                953724,Mrs.,Nanci,D,Osorio,F,nanci.osorio@hotmail.com,7/9/1982,11/7/2003,71321
                138700,Ms.,Maricela,H,Simard,F,maricela.simard@gmail.com,7/21/1988,9/25/2016,88513
                644265,Ms.,Avelina,I,Stoner,F,avelina.stoner@exxonmobil.com,10/1/1988,11/30/2010,157826
                223871,Drs.,Christene,O,Mattison,F,christene.mattison@gmail.com,9/14/1990,9/13/2015,177224
                807262,Mr.,Stefan,O,Maeda,M,stefan.maeda@yahoo.com,3/23/1990,11/5/2011,67028
                368234,Drs.,Gillian,T,Winter,F,gillian.winter@gmail.com,1/17/1960,11/28/1984,103619
                807442,Hon.,Ed,E,Ferrari,M,ed.ferrari@gmail.com,9/27/1981,2/15/2015,145831
                956778,Ms.,Jewell,L,Thies,F,jewell.thies@aol.com,2/16/1991,4/28/2017,153248
                496781,Dr.,Jonathan,Z,Rosa,M,jonathan.rosa@gmail.com,2/19/1981,7/23/2010,198838
                301629,Mr.,Ruben,L,Weissman,M,ruben.weissman@gmail.com,12/28/1995,1/3/2017,48543
                668065,Mrs.,Deja,H,Niemeyer,F,deja.niemeyer@gmail.com,9/4/1961,6/5/2012,130000
                836931,Ms.,Selene,S,Ford,F,selene.ford@aol.com,3/23/1992,10/7/2013,143902
                914698,Ms.,Janeen,M,Norman,F,janeen.norman@gmail.com,1/28/1989,1/5/2012,117383
                872750,Mr.,Johnnie,J,Ibarra,M,johnnie.ibarra@aol.com,1/10/1972,4/14/2013,181385
                813428,Mr.,Emerson,L,Sands,M,emerson.sands@gmail.com,7/8/1978,1/1/2015,106048
                380228,Mr.,Oswaldo,V,Dodd,M,oswaldo.dodd@gmail.com,11/24/1975,11/14/1998,92047
                791100,Ms.,Leone,Y,Buss,F,leone.buss@yahoo.co.uk,1/31/1968,12/31/1996,44080
                613551,Mr.,Jay,Y,Shields,M,jay.shields@gmail.com,3/11/1981,8/26/2013,167893
                147663,Mr.,Douglass,E,Corrigan,M,douglass.corrigan@rediffmail.com,3/17/1973,2/19/2015,86718
                707600,Ms.,Sherita,M,Baugh,F,sherita.baugh@gmail.com,4/20/1995,8/17/2016,49450
                596529,Ms.,Lana,N,Arbuckle,F,lana.arbuckle@shaw.ca,10/22/1967,11/11/1996,193628
                834635,Hon.,Deloise,T,Oyler,F,deloise.oyler@yahoo.com,9/4/1963,1/6/2015,173977
                794524,Mr.,Armand,O,Fortenberry,M,armand.fortenberry@sbcglobal.net,1/20/1992,2/17/2017,184287
                392234,Dr.,Monty,D,Hail,M,monty.hail@gmail.com,6/21/1960,10/30/1983,89286
                444753,Ms.,Judith,G,Owen,F,judith.owen@gmail.com,2/14/1958,5/17/1982,76503
                361359,Hon.,Laureen,Q,Lambert,F,laureen.lambert@exxonmobil.com,2/23/1960,7/31/2007,78201
                777936,Ms.,Nila,W,Traylor,F,nila.traylor@yahoo.com,5/6/1972,4/23/1998,93058
                529020,Dr.,Bernard,P,Bradshaw,M,bernard.bradshaw@yahoo.co.uk,8/18/1969,3/31/2002,98634
                884439,Mrs.,Micheal,A,Sarmiento,F,micheal.sarmiento@aol.com,7/17/1965,3/21/1991,81467
                269950,Mr.,Seth,B,Bilodeau,M,seth.bilodeau@bellsouth.net,5/5/1975,7/6/2002,164287
                258110,Ms.,Tai,O,Saavedra,F,tai.saavedra@hotmail.com,9/15/1995,9/15/2016,51872
                248437,Ms.,Tamra,A,Alessi,F,tamra.alessi@outlook.com,11/19/1977,11/8/2004,90981
                357480,Ms.,Shenika,R,Crittenden,F,shenika.crittenden@walmart.com,10/9/1975,5/27/1998,58060
                845835,Dr.,Bernard,Q,Fessler,M,bernard.fessler@hotmail.co.uk,7/31/1958,4/17/1980,93610
                878763,Mr.,Ernest,N,Chasse,M,ernest.chasse@gmail.com,10/11/1995,7/16/2017,86598
                452667,Mr.,Jeffery,D,Girard,M,jeffery.girard@outlook.com,8/13/1972,6/17/1998,78719
                879556,Prof.,Christoper,I,Moye,M,christoper.moye@aol.com,3/6/1992,8/24/2013,179104
                541976,Mr.,Kelley,Z,Greening,M,kelley.greening@rediffmail.com,2/26/1979,6/22/2001,157705
                892120,Mrs.,Ernestine,U,Beall,F,ernestine.beall@aol.com,7/28/1972,11/20/2005,92635
                662814,Mr.,Archie,R,Folse,M,archie.folse@outlook.com,12/20/1992,1/2/2016,145647
                609576,Prof.,Ronny,C,Legault,M,ronny.legault@gmail.com,4/30/1977,5/2/2010,83203
                492759,Dr.,Eloy,V,Brune,M,eloy.brune@aol.com,12/28/1972,2/6/2015,170760
                283912,Hon.,Melani,A,Ruelas,F,melani.ruelas@gmail.com,1/4/1973,8/15/2007,117415
                532795,Mr.,Carlo,B,Shotwell,M,carlo.shotwell@gmail.com,6/11/1993,9/23/2015,79470
                863566,Prof.,Roland,Z,Antonio,M,roland.antonio@hotmail.com,5/28/1989,1/8/2015,137241
                361215,Mrs.,Lisa,G,Wick,F,lisa.wick@gmail.com,12/25/1995,5/17/2017,162121
                787265,Prof.,Frankie,S,Owings,M,frankie.owings@yahoo.co.in,6/15/1993,5/10/2017,42901
                696561,Mr.,Joe,K,Gowan,M,joe.gowan@comcast.net,4/20/1980,9/6/2013,164300
                906336,Ms.,Melisa,Z,Lomas,F,melisa.lomas@msn.com,10/29/1967,5/25/1998,178886
                630690,Drs.,Jerilyn,J,Decker,F,jerilyn.decker@yahoo.com,8/26/1970,4/15/2014,148791
                630690,Drs.,Jerilyn,J,Decker,F,jerilyn.decker@yahoo.com,8/26/1970,4/15/2014,148791
                630690,Drs.,Jerilyn,J,Decker,F,jerilyn.decker@yahoo.com,8/26/1970,4/15/2014,148791
                785482,Mr.,Geoffrey,J,Lemmons,M,geoffrey.lemmons@gmail.com,3/25/1960,3/15/2008,108088""";
        String[] records = data.split("\n");

        // Creates employees obj from data: removing any duplicates and sorts
        Employees employees = new Employees();
        for (String record: records)
            employees.addEmployee(record);

        // Get employees array
//        Employee[] employeesArray = employees.getEmployees();
//        for (Employee e: employeesArray)
//            System.out.println(e.serialize());
        System.out.println(employees.serialize());
    }
}