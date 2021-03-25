package com.example.atmfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Default extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener{
    TextView txv_sort,txv_bank;
    CheckBox setMap;
    ImageView Radio;
    Spinner Sort,bank;
    long bank_id;


    String[] BankSet;
    String[] BankSet1 = {"請選擇銀行...","臺灣銀行","臺灣土地銀行","合作金庫商業銀行","第一商業銀行","華南商業銀行", "彰化商業銀行","上海商業儲蓄銀行",
            "台北富邦商業銀行","國泰世華商業銀行","高雄銀行", "兆豐國際商業銀行","臺灣中小企業銀行","臺灣新光商業銀行",
            "陽信商業銀行","三信商業銀行","聯邦商業銀行", "元大商業銀行","永豐商業銀行","玉山商業銀行",
            "凱基商業銀行","台新國際商業銀行","日盛國際商業銀行", "中國信託商業銀行"};

    String[] BankSet2 = {"請選擇銀行...","屏東市農會","中華郵政股份有限公司"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default);

        bank = findViewById(R.id.SP_Bank);
        Sort = findViewById(R.id.SP_Sort);
        txv_sort = findViewById(R.id.Txv_Sort);
        txv_bank = findViewById(R.id.Txv_Bank);
        Sort.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(position == 2)
            BankSet = BankSet2;
        else
            BankSet = BankSet1;
        ArrayAdapter<String> BankAd = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,BankSet);
        BankAd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bank.setAdapter(BankAd);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void search(View v)
    {
        if(Sort.getSelectedItemId()==0 || bank.getSelectedItemId()==0)
        {
            Toast.makeText(this,"請選擇類別及銀行後，再搜尋...",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"正在為您搜尋...",Toast.LENGTH_SHORT).show();
            if(BankSet == BankSet1)
            {
                bank_id = bank.getSelectedItemId();
                switch ((int) bank_id)
                {
                    case 1: //臺灣銀行
                        BOT();
                        break;
                    case 2: //臺灣土地銀行
                        LBOT();
                        break;
                    case 3: //合作金庫商業銀行
                        TCB();
                        break;
                    case 4: //第一商業銀行
                        FCB();
                        break;
                    case 5: //華南商業銀行
                        HNCB();
                        break;
                    case 6: //彰化商業銀行
                        CHCB();
                        break;
                    case 7: //上海商業儲蓄銀行
                        TSCSB();
                        break;
                    case 8: //台北富邦商業銀行
                        TFCB();
                        break;
                    case 9: //國泰世華商業銀行
                        CUB();
                        break;
                    case 10: //高雄銀行
                        BOK();
                        break;
                    case 11: //兆豐國際商業銀行
                        MICB();
                        break;
                    case 12: //臺灣中小企業銀行
                        TBB();
                        break;
                    case 13: //新光商業銀行
                        SKCB();
                        break;
                    case 14: //陽信商業銀行
                        SB();
                    case 15: //三信商業銀行
                        CCB();
                        break;
                    case 16: //聯邦商業銀行
                        UBOT();
                        break;
                    case 17:
                        YCB(); //元大商業銀行
                        break;
                    case 18:
                        BS(); //永豐商業銀行
                        break;
                    case 19:
                        ESCB(); //玉山商業銀行
                        break;
                    case 20: //凱基商業銀行
                        KGB();
                        break;
                    case 21: //台新國際商業銀行
                        TIB();
                        break;
                    case 22: //日盛國際商業銀行
                        JICB();
                        break;
                    case 23: //中國信託商業銀行
                        CTBC();
                        break;
                    default:break;
                }
            }
            else
            {
                long bank_id = bank.getSelectedItemId();
                if (bank_id==1)
                {
                    PTFA();
                }
                if(bank_id==2)
                {
                    CWP();
                }
            }
        }
    }
    public void BOT()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",1);
        it.putExtra("BOT_location01_lng",22.671768936036806);
        it.putExtra("BOT_location01_lat",120.48954437725176);
        it.putExtra("BOT_location01_name","臺灣銀行(中屏分行)");

        it.putExtra("BOT_location02_lng",22.672752378290145);
        it.putExtra("BOT_location02_lat",120.4856610820534);
        it.putExtra("BOT_location02_name","臺灣銀行(屏東分行)");

        it.putExtra("BOT_location03_lng",22.683233857599905);
        it.putExtra("BOT_location03_lat",120.48703868390396);
        it.putExtra("BOT_location03_name","屏東地政事務所");

        it.putExtra("BOT_location04_lng",22.6659254610116);
        it.putExtra("BOT_location04_lat",120.50483176742488);
        it.putExtra("BOT_location04_name","國立屏東大學民生校區");

        it.putExtra("BOT_location05_lng",22.658146885610336);
        it.putExtra("BOT_location05_lat",120.51157614291307);
        it.putExtra("BOT_location05_name","國立屏東大學屏商校區");
        startActivity(it);
    }

    public void LBOT()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",2);
        it.putExtra("LBOT_location01_lng",22.670909781271323);
        it.putExtra("LBOT_location01_lat",120.48693319739638);
        it.putExtra("LBOT_location01_name","臺灣土地銀行(屏東分行)");

        it.putExtra("LBOT_location02_lng",22.662255400010995);
        it.putExtra("LBOT_location02_lat",120.4923686084989);
        it.putExtra("LBOT_location02_name","台糖冰店");

        it.putExtra("LBOT_location03_lng",22.67526498555105);
        it.putExtra("LBOT_location03_lat",120.48759418406418);
        it.putExtra("LBOT_location03_name","屏東水利會");

        it.putExtra("LBOT_location04_lng",22.673551977726984);
        it.putExtra("LBOT_location04_lat", 120.49557395506804);
        it.putExtra("LBOT_location04_name","屏東醫院");

        it.putExtra("LBOT_location05_lng",22.68403916030711);
        it.putExtra("LBOT_location05_lat",120.48701709924715);
        it.putExtra("LBOT_location05_name","迪卡儂屏東店");
        startActivity(it);
    }

    public void TCB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",3);
        it.putExtra("TCB_location01_lng",22.674471076200554);
        it.putExtra("TCB_location01_lat",120.48982409739664);
        it.putExtra("TCB_location01_name","太平洋百貨公司(屏東店)");

        it.putExtra("TCB_location02_lng",22.67861746660202);
        it.putExtra("TCB_location02_lat",120.47050225506813);
        it.putExtra("TCB_location02_name","空軍基地");

        it.putExtra("TCB_location03_lng",22.673039777202984);
        it.putExtra("TCB_location03_lat", 120.48920193972505);
        it.putExtra("TCB_location03_name","合作金庫商業銀行(屏東分行)");

        it.putExtra("TCB_location04_lng",22.670264583109923);
        it.putExtra("TCB_location04_lat",120.49178726856073);
        it.putExtra("TCB_location04_name","合作金庫商業銀行(屏南分行)");

        it.putExtra("TCB_location05_lng",22.68220489603158);
        it.putExtra("TCB_location05_lat",120.50262333861727);
        it.putExtra("TCB_location05_name","基督教醫院");

        it.putExtra("TCB_location06_lng",22.68096565056825);
        it.putExtra("TCB_location06_lat",120.50527689402388);
        it.putExtra("TCB_location06_name","基督教醫院瑞光分院");
        startActivity(it);
    }

    public void FCB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",4);
        it.putExtra("FCB_location01_lng",22.67134178155185);
        it.putExtra("FCB_location01_lat",120.48973575506811);
        it.putExtra("FCB_location01_name","第一商業銀行(屏東分行)");

        it.putExtra("FCB_location02_lng",22.643244870380666);
        it.putExtra("FCB_location02_lat",120.61088335977327);
        it.putExtra("FCB_location02_name","屏東科大圖書館");
        startActivity(it);
    }

    public void HNCB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",5);
        it.putExtra("HNCB_location01_lng",22.657931454266986);
        it.putExtra("HNCB_location01_lat",120.48622313111011);
        it.putExtra("HNCB_location01_name","中華電信(屏東營運處)");

        it.putExtra("HNCB_location02_lng",22.67431579496401);
        it.putExtra("HNCB_location02_lat",120.48976214444606);
        it.putExtra("HNCB_location02_name","豐屏實業(SOGO)");

        it.putExtra("HNCB_location03_lng",22.670220723566874);
        it.putExtra("HNCB_location03_lat",120.48944908201926);
        it.putExtra("HNCB_location03_name","華南商業銀行(屏東分行)");
        startActivity(it);
    }

    public void CHCB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",6);
        it.putExtra("CHCB_location01_lng",22.66061502186262);
        it.putExtra("CHCB_location01_lat", 120.46123437736215);
        it.putExtra("CHCB_location01_name","宏森木業公司");

        it.putExtra("CHCB_location02_lng",22.67392887540553);
        it.putExtra("CHCB_location02_lat",120.4891621820534);
        it.putExtra("CHCB_location02_name","彰化商業銀行(屏東分行)");

        it.putExtra("CHCB_location03_lng",22.654686213240982);
        it.putExtra("CHCB_location03_lat",120.49781761273947);
        it.putExtra("CHCB_location03_name","屏東縣政府警察局交通隊");
        startActivity(it);
    }

    public void TSCSB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",7);
        it.putExtra("TSCSB_location01_lng",22.684951650851406);
        it.putExtra("TSCSB_location01_lat", 120.49437621273984);
        it.putExtra("TSCSB_location01_name","上海商業儲蓄銀行(屏東分行)");
        startActivity(it);
    }

    public void TFCB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",8);
        it.putExtra("TFCB_location01_lng",22.673825429779562);
        it.putExtra("TFCB_location01_lat", 120.49197481111119);
        it.putExtra("TFCB_location01_name","台北富邦商業銀行(屏東分行)");

        it.putExtra("TFCB_location02_lng",22.67332620610816);
        it.putExtra("TFCB_location02_lat", 120.49338986778773);
        it.putExtra("TFCB_location02_name","環球購物中心屏東店");
        startActivity(it);
    }

    public void CUB()
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",9);
        it.putExtra("CUB_location01_lng",22.681557350397615);
        it.putExtra("CUB_location01_lat", 120.47484332349308);
        it.putExtra("CUB_location01_name","全家超商(屏東大崇店)");

        it.putExtra("CUB_location02_lng",22.635746213447895);
        it.putExtra("CUB_location02_lat", 120.48222126334356);
        it.putExtra("CUB_location02_name","全家超商(屏東公館店)");

        it.putExtra("CUB_location03_lng",22.665428841036377);
        it.putExtra("CUB_location03_lat", 120.50168857606124);
        it.putExtra("CUB_location03_name","全家超商(屏東民榮店)");

        it.putExtra("CUB_location04_lng",22.660430587517045);
        it.putExtra("CUB_location04_lat", 120.51321040197605);
        it.putExtra("CUB_location04_name","全家超商(屏東安心店)");

        it.putExtra("CUB_location05_lng",22.656139329405406);
        it.putExtra("CUB_location05_lat", 120.47686407178291);
        it.putExtra("CUB_location05_name","全家超商(屏東武成店)");

        it.putExtra("CUB_location06_lng",22.657233579239414);
        it.putExtra("CUB_location06_lat", 120.51089121736302);
        it.putExtra("CUB_location06_name","全家超商(屏東商大店)");

        it.putExtra("CUB_location07_lng",22.682534383144752);
        it.putExtra("CUB_location07_lat", 120.50209952103442);
        it.putExtra("CUB_location07_name","全家超商(屏東華敬店)");

        it.putExtra("CUB_location08_lng",22.6737278205107);
        it.putExtra("CUB_location08_lat", 120.4920764023746);
        it.putExtra("CUB_location08_name","全家超商(屏東愛園店)");

        it.putExtra("CUB_location09_lng",22.67389169840016);
        it.putExtra("CUB_location09_lat", 120.51082469650821);
        it.putExtra("CUB_location09_name","全家超商(屏東瑞光店)");

        it.putExtra("CUB_location10_lng",22.68704115145951);
        it.putExtra("CUB_location10_lat", 120.49446751273993);
        it.putExtra("CUB_location10_name","全家超商(屏東廣興店)");

        it.putExtra("CUB_location11_lng",22.655491388682087);
        it.putExtra("CUB_location11_lat", 120.50448658765323);
        it.putExtra("CUB_location11_name","全家超商(屏東歸來店)");

        it.putExtra("CUB_location12_lng",22.67359157640073);
        it.putExtra("CUB_location12_lat", 120.49557395506804);
        it.putExtra("CUB_location12_name","全家超商(屏東醫院店)");

        it.putExtra("CUB_location13_lng",22.671568480681245);
        it.putExtra("CUB_location13_lat", 120.48083353972493);
        it.putExtra("CUB_location13_name","全聯(屏東民族)");

        it.putExtra("CUB_location14_lng",22.683088158883805);
        it.putExtra("CUB_location14_lat", 120.50522818205361);
        it.putExtra("CUB_location14_name","全聯(屏東大連)");

        it.putExtra("CUB_location15_lng",22.68701175023167);
        it.putExtra("CUB_location15_lat", 120.496503368561);
        it.putExtra("CUB_location15_name","全聯(屏東中正)");

        it.putExtra("CUB_location16_lng",22.661301300927235);
        it.putExtra("CUB_location16_lat", 120.47901091273934);
        it.putExtra("CUB_location16_name","全聯(屏東永大)");

        it.putExtra("CUB_location17_lng",22.683480155065944);
        it.putExtra("CUB_location17_lat", 120.48588840109754);
        it.putExtra("CUB_location17_name","全聯(屏東自由)");

        it.putExtra("CUB_location18_lng",22.66860031059648);
        it.putExtra("CUB_location18_lat", 120.49426493745618);
        it.putExtra("CUB_location18_name","全聯(屏東林森)");

        it.putExtra("CUB_location19_lng",22.677036169123234);
        it.putExtra("CUB_location19_lat", 120.49901252438187);
        it.putExtra("CUB_location19_name","全聯(屏東勝利)");

        it.putExtra("CUB_location20_lng",22.673578539073475);
        it.putExtra("CUB_location20_lat", 120.49307645789533);
        it.putExtra("CUB_location20_name","全聯(屏東復興南)");

        it.putExtra("CUB_location21_lng",22.650934717907496);
        it.putExtra("CUB_location21_lat", 120.48611139739612);
        it.putExtra("CUB_location21_name","全聯(屏東瑞民)");

        it.putExtra("CUB_location22_lng",22.687639475524527);
        it.putExtra("CUB_location22_lat", 120.4832160459515);
        it.putExtra("CUB_location22_name","全聯(屏東廣東)");

        it.putExtra("CUB_location23_lng",22.675503873124793);
        it.putExtra("CUB_location23_lat", 120.48966205506811);
        it.putExtra("CUB_location23_name","國泰世華商業銀行(屏東分行)");

        it.putExtra("CUB_location24_lng",22.677183859397292);
        it.putExtra("CUB_location24_lat", 120.49071885373165);
        it.putExtra("CUB_location24_name","國壽屏東中正大樓");

        it.putExtra("CUB_location25_lng",22.679181725673125);
        it.putExtra("CUB_location25_lat", 120.50618205652424);
        it.putExtra("CUB_location25_name","萊爾富(屏東好豐年店)");

        it.putExtra("CUB_location26_lng",22.688833746677616);
        it.putExtra("CUB_location26_lat", 120.47537455321795);
        it.putExtra("CUB_location26_name","萊爾富(屏東崇朝店)");

        it.putExtra("CUB_location27_lng",22.666659429820374);
        it.putExtra("CUB_location27_lat", 120.50632712902657);
        it.putExtra("CUB_location27_name","萊爾富超商(屏東大學店)");

        it.putExtra("CUB_location28_lng",22.669139385087284);
        it.putExtra("CUB_location28_lat", 120.48638788205335);
        it.putExtra("CUB_location28_name","萊爾富超商(屏東太陽城店)");

        it.putExtra("CUB_location29_lng",22.683621155807238);
        it.putExtra("CUB_location29_lat", 120.50183352623247);
        it.putExtra("CUB_location29_name","萊爾富超商(屏東屏敬店)");

        it.putExtra("CUB_location30_lng",22.664970531603633);
        it.putExtra("CUB_location30_lat", 120.48135683638523);
        it.putExtra("CUB_location30_name","萊爾富超商(屏東建欣店)");
        startActivity(it);
    }

    public void BOK() //高雄銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",10);
        it.putExtra("BOK_location01_lng",22.677140209165913);
        it.putExtra("BOK_location01_lat", 120.49061273081716);
        it.putExtra("BOK_location01_name","高雄銀行(屏東分行)");
        startActivity(it);
    }

    public void MICB() //兆豐國際商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",11);
        it.putExtra("MICB_location01_lng",22.648470381935113);
        it.putExtra("MICB_location01_lat", 120.45041919302001);
        it.putExtra("MICB_location01_name","台灣本田(股)公司");

        it.putExtra("MICB_location02_lng",22.67038183960202);
        it.putExtra("MICB_location02_lat",120.4843735400066);
        it.putExtra("MICB_location02_name","兆豐國際商業銀行(屏東分行)");

        it.putExtra("MICB_location03_lng",22.682145347470037);
        it.putExtra("MICB_location03_lat",120.50262094512915);
        it.putExtra("MICB_location03_name","基督教醫院");
        startActivity(it);
    }

    public void TBB() //台灣中小企業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",12);
        it.putExtra("TBB_location01_lng",22.695446529994065);
        it.putExtra("TBB_location01_lat", 120.49260431088973);
        it.putExtra("TBB_location01_name","台電屏東區營業處");

        it.putExtra("TBB_location02_lng",22.67042237926437);
        it.putExtra("TBB_location02_lat",120.48993218975548);
        it.putExtra("TBB_location02_name","台灣中小企業銀行(屏東分行)");

        it.putExtra("TBB_location03_lng",22.64214563559926);
        it.putExtra("TBB_location03_lat",120.45411769841373);
        it.putExtra("TBB_location03_name","屏東加工出口區");
        startActivity(it);
    }


    public void SKCB() //新光商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",13);
        it.putExtra("SKCB_location01_lng",22.65209833727142);
        it.putExtra("SKCB_location01_lat", 120.48432466819303);
        it.putExtra("SKCB_location01_name","和生大樓");

        it.putExtra("SKCB_location02_lng",22.67177575470546);
        it.putExtra("SKCB_location02_lat", 120.4990783543605);
        it.putExtra("SKCB_location02_name","新光商業銀行(東園分行)");

        it.putExtra("SKCB_location03_lng",22.67466245753273);
        it.putExtra("SKCB_location03_lat", 120.48954100867242);
        it.putExtra("SKCB_location03_name","新光商業銀行(屏東分行)");

        it.putExtra("SKCB_location04_lng",22.682748466899408);
        it.putExtra("SKCB_location04_lat", 120.49796401490339);
        it.putExtra("SKCB_location04_name","家樂福(屏東店)");

        it.putExtra("SKCB_location05_lng",22.663364504576307);
        it.putExtra("SKCB_location05_lat", 120.48905727002177);
        it.putExtra("SKCB_location05_name","家樂福(新屏店)");

        it.putExtra("SKCB_location06_lng",22.669889871413968);
        it.putExtra("SKCB_location06_lat", 120.49456081533094);
        it.putExtra("SKCB_location06_name","新壽屏東大樓");
        startActivity(it);
    }

    public void SB() //陽信商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",14);
        it.putExtra("SB_location01_lng",22.673566974453657);
        it.putExtra("SB_location01_lat", 120.48946714615504);
        it.putExtra("SB_location01_name","陽信商業銀行(屏東分行)");

        it.putExtra("SB_location02_lng",22.68123757522102);
        it.putExtra("SB_location02_lat", 120.4844245832959);
        it.putExtra("SB_location02_name","寶建醫院");
        startActivity(it);
    }

    public void CCB() //三信商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",15);
        it.putExtra("CCB_location01_lng",22.66898008094611);
        it.putExtra("CCB_location01_lat", 120.4860747160005);
        it.putExtra("CCB_location01_name","屏東火車站");
        startActivity(it);
    }

    public void UBOT() //聯邦商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",16);
        it.putExtra("UBOT_location01_lng",22.65757521564613);
        it.putExtra("UBOT_location01_lat",120.48421567866328);
        it.putExtra("UBOT_location01_name","OK超商屏東棒球店");

        it.putExtra("UBOT_location02_lng",22.67326107215056);
        it.putExtra("UBOT_location02_lat",120.49126513230961);
        it.putExtra("UBOT_location02_name","小北百貨林森店");

        it.putExtra("UBOT_location03_lng",22.672848886444395);
        it.putExtra("UBOT_location03_lat", 120.48382584339072);
        it.putExtra("UBOT_location03_name","小北百貨重慶店");

        it.putExtra("UBOT_location04_lng",22.69089931030156);
        it.putExtra("UBOT_location04_lat",120.50078154260115);
        it.putExtra("UBOT_location04_name","小北百貨莊敬店");

        it.putExtra("UBOT_location05_lng",22.68776623960225);
        it.putExtra("UBOT_location05_lat",120.48913116961965);
        it.putExtra("UBOT_location05_name","小北百貨廣東店");

        it.putExtra("UBOT_location06_lng",22.670892620678902);
        it.putExtra("UBOT_location06_lat",120.48314057160981);
        it.putExtra("UBOT_location06_name","聯邦商業銀行(屏東分行)");
        startActivity(it);
    }

    public void YCB() //元大商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",17);
        it.putExtra("YCB_location01_lng",22.670042912697856);
        it.putExtra("YCB_location01_lat", 120.49179737941634);
        it.putExtra("YCB_location01_name","屏東民生收付處");

        it.putExtra("YCB_location02_lng",22.677073967748882);
        it.putExtra("YCB_location02_lat", 120.49096076638457);
        it.putExtra("YCB_location02_name","屏南收付處");

        it.putExtra("YCB_location03_lng",22.67409252782302);
        it.putExtra("YCB_location03_lat", 120.49085994280782);
        it.putExtra("YCB_location03_name","太平洋百貨屏東二館");

        it.putExtra("YCB_location04_lng",22.674103821589682);
        it.putExtra("YCB_location04_lat", 120.48969579982638);
        it.putExtra("YCB_location04_name","太平洋百貨屏東店");

        it.putExtra("YCB_location05_lng",22.685467451432835);
        it.putExtra("YCB_location05_lat", 120.4963501266219);
        it.putExtra("YCB_location05_name","元大商業銀行(屏東分行)");

        it.putExtra("YCB_location06_lng",22.67327411153368);
        it.putExtra("YCB_location06_lat", 120.49954483352367);
        it.putExtra("YCB_location06_name","元大商業銀行(屏榮分行)");

        it.putExtra("YCB_location07_lng",22.66107254048044);
        it.putExtra("YCB_location07_lat", 120.51016263454196);
        it.putExtra("YCB_location07_name","特力屋屏東店");
        startActivity(it);
    }

    public void BS() //永豐商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",18);
        it.putExtra("BS_location01_lng",22.671624768704522);
        it.putExtra("BS_location01_lat", 120.4891018860563);
        it.putExtra("BS_location01_name","永豐商業銀行(屏東分行)");

        it.putExtra("BS_location02_lng",22.681612668201538);
        it.putExtra("BS_location02_lat", 120.48479452715463);
        it.putExtra("BS_location02_name","萊爾富(迪化店)");
        startActivity(it);
    }

    public void ESCB() //玉山商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",19);
        it.putExtra("ESCB_location01_lng",22.66896666142207);
        it.putExtra("ESCB_location01_lat", 120.48606625555982);
        it.putExtra("ESCB_location01_name","全家(車頭店)");

        it.putExtra("ESCB_location02_lng",22.6713221748485);
        it.putExtra("ESCB_location02_lat", 120.48713520677133);
        it.putExtra("ESCB_location02_name","玉山商業銀行(屏東分行)");

        it.putExtra("ESCB_location03_lng",22.68340471751479);
        it.putExtra("ESCB_location03_lat", 120.48296805338217);
        it.putExtra("ESCB_location03_name","家樂福屏東自由店");

        it.putExtra("ESCB_location04_lng",22.682981474049292);
        it.putExtra("ESCB_location04_lat", 120.49795338205357);
        it.putExtra("ESCB_location04_name","家樂福屏東店");

        it.putExtra("ESCB_location05_lng",22.663360085885902);
        it.putExtra("ESCB_location05_lat", 120.4890579958679);
        it.putExtra("ESCB_location05_name","家樂福新屏店");
        startActivity(it);
    }

    public void KGB() //凱基商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",20);
        it.putExtra("KGB_location01_lng",22.68272502172001);
        it.putExtra("KGB_location01_lat", 120.49910871383145);
        it.putExtra("KGB_location01_name","凱基商業銀行(屏東分行)");
        startActivity(it);
    }

    public void TIB() //台新
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",21);
        it.putExtra("TIB_location01_lng",22.664931604915417);
        it.putExtra("TIB_location01_lat", 120.47262528083529);
        it.putExtra("TIB_location01_name","OK超商(屏東建國)");

        it.putExtra("TIB_location02_lng",22.676484676166286);
        it.putExtra("TIB_location02_lat", 120.5035367840075);
        it.putExtra("TIB_location02_name","OK超商(屏東建豐)");

        it.putExtra("TIB_location03_lng",22.670832519145986);
        it.putExtra("TIB_location03_lat", 120.48125852942844);
        it.putExtra("TIB_location03_name","全家超商(屏東大埔店)");

        it.putExtra("TIB_location04_lng",22.68246359962539);
        it.putExtra("TIB_location04_lat", 120.49310034051798);
        it.putExtra("TIB_location04_name","全家超商(屏東中華店)");

        it.putExtra("TIB_location05_lng",22.68886207553874);
        it.putExtra("TIB_location05_lat", 120.49850386954962);
        it.putExtra("TIB_location05_name","全家超商(屏東正裕店)");

        it.putExtra("TIB_location06_lng",22.671190316630224);
        it.putExtra("TIB_location06_lat", 120.49816931055742);
        it.putExtra("TIB_location06_name","全家超商(屏東民享店)");

        it.putExtra("TIB_location07_lng",22.66713118724454);
        it.putExtra("TIB_location07_lat", 120.48107331117905);
        it.putExtra("TIB_location07_name","全家超商(屏東昌賢店)");

        it.putExtra("TIB_location08_lng",22.66903330603919);
        it.putExtra("TIB_location08_lat", 120.47446464711963);
        it.putExtra("TIB_location08_name","全家超商(屏東長安店)");

        it.putExtra("TIB_location09_lng",22.673909181967996);
        it.putExtra("TIB_location09_lat", 120.5014401062428);
        it.putExtra("TIB_location09_name","全家超商(屏東屏榮店)");

        it.putExtra("TIB_location10_lng",22.678940540255756);
        it.putExtra("TIB_location10_lat", 120.47956726371707);
        it.putExtra("TIB_location10_name","全家超商(屏東徐州店)");

        it.putExtra("TIB_location11_lng",22.68756242003715);
        it.putExtra("TIB_location11_lat", 120.48615497462069);
        it.putExtra("TIB_location11_name","全家超商(屏東勝利店)");

        it.putExtra("TIB_location12_lng",22.661342023794077);
        it.putExtra("TIB_location12_lat", 120.47908989384162);
        it.putExtra("TIB_location12_name","全家超商(屏東華盛店)");

        it.putExtra("TIB_location13_lng",22.664654043480976);
        it.putExtra("TIB_location13_lat", 120.49114348389467);
        it.putExtra("TIB_location13_name","全家超商(屏東福光店)");

        it.putExtra("TIB_location14_lng",22.68022120051589);
        it.putExtra("TIB_location14_lat", 120.50185776619308);
        it.putExtra("TIB_location14_name","全家超商(屏東豐榮店)");

        it.putExtra("TIB_location15_lng",22.672921847235568);
        it.putExtra("TIB_location15_lat", 120.49944967099063);
        it.putExtra("TIB_location15_name","台新國際商業銀行(屏東分行)");

        it.putExtra("TIB_location16_lng",22.683619367478244);
        it.putExtra("TIB_location16_lat", 120.48605775319956);
        it.putExtra("TIB_location16_name","丁丁屏東自由");

        it.putExtra("TIB_location17_lng",22.68857516996056);
        it.putExtra("TIB_location17_lat", 120.49463687298676);
        it.putExtra("TIB_location17_name","屏東國稅局");

        it.putExtra("TIB_location18_lng",22.673344427370363);
        it.putExtra("TIB_location18_lat", 120.49340005948333);
        it.putExtra("TIB_location18_name","屏東環球購物中心");
        startActivity(it);
    }
    public void JICB() //日盛國際商業銀行
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",22);
        it.putExtra("JICB_location01_lng",22.6729552316745);
        it.putExtra("JICB_location01_lat", 120.49322330264197);
        it.putExtra("JICB_location01_name","日盛國際商業銀行(屏東分行)");
        startActivity(it);
    }

    public void CTBC() //中國信託
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",23);
        it.putExtra("CTBC_location01_lng",22.683065429973762);
        it.putExtra("CTBC_location01_lat", 120.49194755340943);
        it.putExtra("CTBC_location01_name","中國信託商業銀行(屏東分行)");

        it.putExtra("CTBC_location02_lng",22.673287810840442);
        it.putExtra("CTBC_location02_lat", 120.49339918257253);
        it.putExtra("CTBC_location02_name","屏東環球購物中心");

        it.putExtra("CTBC_location03_lng",22.635912522955373);
        it.putExtra("CTBC_location03_lat", 120.48270523296789);
        it.putExtra("CTBC_location03_name","(7-ELEVEN公華門市)");

        it.putExtra("CTBC_location04_lng",22.687915257644903);
        it.putExtra("CTBC_location04_lat", 120.49084628552527);
        it.putExtra("CTBC_location04_name","(7-ELEVEN公裕門市)");

        it.putExtra("CTBC_location05_lng",22.65734403649461);
        it.putExtra("CTBC_location05_lat", 120.47635910937952);
        it.putExtra("CTBC_location05_name","(7-ELEVEN永大門市)");

        it.putExtra("CTBC_location06_lng",22.663151603385337);
        it.putExtra("CTBC_location06_lat", 120.48778145593222);
        it.putExtra("CTBC_location06_name","(7-ELEVEN永發門市)");

        it.putExtra("CTBC_location07_lng",22.672283803937646);
        it.putExtra("CTBC_location07_lat", 120.48346378941135);
        it.putExtra("CTBC_location07_name","(7-ELEVEN光榮門市)");

        it.putExtra("CTBC_location08_lng",22.67879908714883);
        it.putExtra("CTBC_location08_lat", 120.4748997326789);
        it.putExtra("CTBC_location08_name","(7-ELEVEN同勝門市)");

        it.putExtra("CTBC_location09_lng",22.699650304973698);
        it.putExtra("CTBC_location09_lat", 120.50939732949554);
        it.putExtra("CTBC_location09_name","(7-ELEVEN合家宜門市)");

        it.putExtra("CTBC_location10_lng",22.651982057056394);
        it.putExtra("CTBC_location10_lat", 120.48613655319954);
        it.putExtra("CTBC_location10_name","(7-ELEVEN和興門市)");

        it.putExtra("CTBC_location11_lng",22.686394091315663);
        it.putExtra("CTBC_location11_lat", 120.47180434048342);
        it.putExtra("CTBC_location11_name","(7-ELEVEN東山河門市");

        it.putExtra("CTBC_location12_lng",22.683344743337706);
        it.putExtra("CTBC_location12_lat", 120.48093972101303);
        it.putExtra("CTBC_location12_name","(7-ELEVEN欣欣鼎門市");

        it.putExtra("CTBC_location13_lng",22.669805359829873);
        it.putExtra("CTBC_location13_lat", 120.49203016762459);
        it.putExtra("CTBC_location13_name","(7-ELEVEN金樹門市");

        it.putExtra("CTBC_location14_lng",22.67735090817877);
        it.putExtra("CTBC_location14_lat", 120.52725834499272);
        it.putExtra("CTBC_location14_name","(7-ELEVEN長治門市");

        it.putExtra("CTBC_location15_lng",22.665511124803274);
        it.putExtra("CTBC_location15_lat", 120.4788854136129);
        it.putExtra("CTBC_location15_name","(7-ELEVEN厚生門市");

        it.putExtra("CTBC_location16_lng",22.662192406452313);
        it.putExtra("CTBC_location16_lat", 120.5071339863038);
        it.putExtra("CTBC_location16_name","(7-ELEVEN屏生門市");

        it.putExtra("CTBC_location17_lng",22.64098463607612);
        it.putExtra("CTBC_location17_lat", 120.59536980629461);
        it.putExtra("CTBC_location17_name","(7-ELEVEN屏科大門市");

        it.putExtra("CTBC_location18_lng",22.669451192205024);
        it.putExtra("CTBC_location18_lat", 120.48575091055048);
        it.putExtra("CTBC_location18_name","(7-ELEVEN屏棧門市");

        it.putExtra("CTBC_location19_lng",22.664578958566665);
        it.putExtra("CTBC_location19_lat", 120.51163592419684);
        it.putExtra("CTBC_location19_name","(7-ELEVEN香楊門市");

        it.putExtra("CTBC_location20_lng",22.669869177715928);
        it.putExtra("CTBC_location20_lat", 120.48596796082914);
        it.putExtra("CTBC_location20_name","(7-ELEVEN國站門市");

        it.putExtra("CTBC_location21_lng",22.662852601450275);
        it.putExtra("CTBC_location21_lat", 120.4787196457227);
        it.putExtra("CTBC_location21_name","(7-ELEVEN崇武門市");

        it.putExtra("CTBC_location22_lng",22.68281584005043);
        it.putExtra("CTBC_location22_lat", 120.47491407473288);
        it.putExtra("CTBC_location22_name","(7-ELEVEN崇蘭門市");

        it.putExtra("CTBC_location23_lng",22.65587059497434);
        it.putExtra("CTBC_location23_lat", 120.4817687712073);
        it.putExtra("CTBC_location23_name","(7-ELEVEN統棒門市");

        it.putExtra("CTBC_location24_lng",22.687388160526375);
        it.putExtra("CTBC_location24_lat", 120.48093842358239);
        it.putExtra("CTBC_location24_name","(7-ELEVEN翊聖門市)");

        it.putExtra("CTBC_location25_lng",22.679218846259065);
        it.putExtra("CTBC_location25_lat", 120.48100122228426);
        it.putExtra("CTBC_location25_name","(7-ELEVEN博勝門市)");

        it.putExtra("CTBC_location26_lng",22.647507457951193);
        it.putExtra("CTBC_location26_lat", 120.48566774813536);
        it.putExtra("CTBC_location26_name","(7-ELEVEN堡勤門市)");

        it.putExtra("CTBC_location27_lng",22.686925185499558);
        it.putExtra("CTBC_location27_lat", 120.5035404676801);
        it.putExtra("CTBC_location27_name","(7-ELEVEN華敬門市)");

        it.putExtra("CTBC_location28_lng",22.660173811302453);
        it.putExtra("CTBC_location28_lat", 120.47296563057735);
        it.putExtra("CTBC_location28_name","(7-ELEVEN愛買門市)");

        it.putExtra("CTBC_location29_lng",22.683483847404837);
        it.putExtra("CTBC_location29_lat", 120.49050063128978);
        it.putExtra("CTBC_location29_name","(7-ELEVEN新自孝門市)");

        it.putExtra("CTBC_location30_lng",22.683719772805095);
        it.putExtra("CTBC_location30_lat", 120.50797584742338);
        it.putExtra("CTBC_location30_name","(7-ELEVEN新德慧門市)");

        it.putExtra("CTBC_location31_lng",22.673973964793195);
        it.putExtra("CTBC_location31_lat", 120.4916273698137);
        it.putExtra("CTBC_location31_name","(7-ELEVEN新樂興門市)");

        it.putExtra("CTBC_location32_lng",22.671949414921066);
        it.putExtra("CTBC_location32_lat", 120.49936398039637);
        it.putExtra("CTBC_location32_name","(7-ELEVEN新豐榮門市)");

        it.putExtra("CTBC_location33_lng",22.66011987190286);
        it.putExtra("CTBC_location33_lat", 120.51119670223255);
        it.putExtra("CTBC_location33_name","(7-ELEVEN瑞光門市)");

        it.putExtra("CTBC_location34_lng",22.66470823436928);
        it.putExtra("CTBC_location34_lat", 120.490957372743);
        it.putExtra("CTBC_location34_name","(7-ELEVEN瑞廣門市)");

        it.putExtra("CTBC_location35_lng",22.683526076788958);
        it.putExtra("CTBC_location35_lat", 120.4981167440693);
        it.putExtra("CTBC_location35_name","(7-ELEVEN廣大門市)");

        it.putExtra("CTBC_location36_lng",22.68941568563677);
        it.putExtra("CTBC_location36_lat", 120.49890450922673);
        it.putExtra("CTBC_location36_name","(7-ELEVEN廣正門市)");

        it.putExtra("CTBC_location37_lng",22.68753296249414);
        it.putExtra("CTBC_location37_lat", 120.48498680387996);
        it.putExtra("CTBC_location37_name","(7-ELEVEN廣吉門市)");

        it.putExtra("CTBC_location38_lng",22.6772082437626);
        it.putExtra("CTBC_location38_lat", 120.50100796703653);
        it.putExtra("CTBC_location38_name","(7-ELEVEN廣豐門市)");

        it.putExtra("CTBC_location39_lng",22.6790192983541);
        it.putExtra("CTBC_location39_lat", 120.47788286097985);
        it.putExtra("CTBC_location39_name","(7-ELEVEN潭墘門市)");

        it.putExtra("CTBC_location40_lng",22.65083930607195);
        it.putExtra("CTBC_location40_lat", 120.46431550687515);
        it.putExtra("CTBC_location40_name","(7-ELEVEN頭前溪門市)");

        it.putExtra("CTBC_location41_lng",22.676111617810932);
        it.putExtra("CTBC_location41_lat", 120.50891335560762);
        it.putExtra("CTBC_location41_name","(7-ELEVEN薇豐門市)");

        it.putExtra("CTBC_location42_lng",22.659591546046993);
        it.putExtra("CTBC_location42_lat", 120.50561796369915);
        it.putExtra("CTBC_location42_name","(7-ELEVEN歸來門市)");
        startActivity(it);
    }

    public void PTFA() //屏東市農會
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",24);
        it.putExtra("PTFA_location01_lng",22.666705299884043);
        it.putExtra("PTFA_location01_lat", 120.49900647610627);
        it.putExtra("PTFA_location01_name","屏東市農會");
        startActivity(it);
    }

    public void CWP() //中華郵政
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",25);
        it.putExtra("CWP_location01_lng",22.664766503697788);
        it.putExtra("CWP_location01_lat", 120.47606630947874);
        it.putExtra("CWP_location01_name","大武營區");

        it.putExtra("CWP_location02_lng",22.65510813084213);
        it.putExtra("CWP_location02_lat", 120.46649463540848);
        it.putExtra("CWP_location02_name","水利局第七區工程處");

        it.putExtra("CWP_location03_lng",22.670936439260352);
        it.putExtra("CWP_location03_lat", 120.48258572751467);
        it.putExtra("CWP_location03_name","大埔郵局");

        it.putExtra("CWP_location04_lng",22.68217959241689);
        it.putExtra("CWP_location04_lat", 120.49177439982641);
        it.putExtra("CWP_location04_name","中正路郵局");

        it.putExtra("CWP_location05_lng",22.669417749004992);
        it.putExtra("CWP_location05_lat", 120.47658958111248);
        it.putExtra("CWP_location05_name","六塊厝郵局");

        it.putExtra("CWP_location06_lng",22.638091538703495);
        it.putExtra("CWP_location06_lat", 120.48507365992941);
        it.putExtra("CWP_location06_name","公館郵局");

        it.putExtra("CWP_location07_lng",22.668929006032396);
        it.putExtra("CWP_location07_lat", 120.486063588672);
        it.putExtra("CWP_location07_name","屏東火車站");

        it.putExtra("CWP_location08_lng",22.67582576669308);
        it.putExtra("CWP_location08_lat", 120.4888520397058);
        it.putExtra("CWP_location08_name","北平路郵局");

        it.putExtra("CWP_location09_lng",22.670514087597322);
        it.putExtra("CWP_location09_lat", 120.49160368533813);
        it.putExtra("CWP_location09_name","民生路郵局");

        it.putExtra("CWP_location10_lng",22.655810026408794);
        it.putExtra("CWP_location10_lat", 120.4815145750592);
        it.putExtra("CWP_location10_name","永安郵局");

        it.putExtra("CWP_location11_lng",22.67145744512256);
        it.putExtra("CWP_location11_lat", 120.49776197690295);
        it.putExtra("CWP_location11_name","林森路郵局");

        it.putExtra("CWP_location12_lng",22.656482632644003);
        it.putExtra("CWP_location12_lat", 120.48335824374273);
        it.putExtra("CWP_location12_name","屏東法院");

        it.putExtra("CWP_location13_lng",22.665454954310448);
        it.putExtra("CWP_location13_lat", 120.47896456855567);
        it.putExtra("CWP_location13_name","厚生郵局");

        it.putExtra("CWP_location14_lng",22.700886040300013);
        it.putExtra("CWP_location14_lat", 120.5108602247092);
        it.putExtra("CWP_location14_name","海豐郵局");

        it.putExtra("CWP_location15_lng",22.686654335556256);
        it.putExtra("CWP_location15_lat", 120.48764144857546);
        it.putExtra("CWP_location15_name","屏東高中");

        it.putExtra("CWP_location16_lng",22.68774252688409);
        it.putExtra("CWP_location16_lat", 120.48539028293297);
        it.putExtra("CWP_location16_name","崇蘭郵局");

        it.putExtra("CWP_location17_lng",22.669724944804816);
        it.putExtra("CWP_location17_lat", 120.5018272807263);
        it.putExtra("CWP_location17_name","屏東大學林森校區");

        it.putExtra("CWP_location18_lng",22.679353635202315);
        it.putExtra("CWP_location18_lat", 120.48593590293314);
        it.putExtra("CWP_location18_name","勝利路郵局");

        it.putExtra("CWP_location19_lng",22.656826112435567);
        it.putExtra("CWP_location19_lat", 120.5165403040099);
        it.putExtra("CWP_location19_name","屏東郵局郵務大樓");

        it.putExtra("CWP_location20_lng",22.692819067680325);
        it.putExtra("CWP_location20_lat", 120.48900699504655);
        it.putExtra("CWP_location20_name","屏東監理站");

        it.putExtra("CWP_location21_lng",22.680448986096735);
        it.putExtra("CWP_location21_lat", 120.50179798902879);
        it.putExtra("CWP_location21_name","廣東路郵局");

        it.putExtra("CWP_location22_lng",22.648460241878645);
        it.putExtra("CWP_location22_lat", 120.46473725642012);
        it.putExtra("CWP_location22_name","頭前溪郵局");

        it.putExtra("CWP_location23_lng",22.664285466110645);
        it.putExtra("CWP_location23_lat", 120.50339252040841);
        it.putExtra("CWP_location23_name","歸來郵局");
        startActivity(it);
    }

    public void fast_search(View v)
    {
        Intent it = new Intent(this,MapsActivity.class);
        it.putExtra("bank_choose",26);
        it.putExtra("BOT_location01_lng",22.671768936036806);
        it.putExtra("BOT_location01_lat",120.48954437725176);
        it.putExtra("BOT_location01_name","臺灣銀行(中屏分行)");

        it.putExtra("BOT_location02_lng",22.672752378290145);
        it.putExtra("BOT_location02_lat",120.4856610820534);
        it.putExtra("BOT_location02_name","臺灣銀行(屏東分行)");

        it.putExtra("BOT_location03_lng",22.683233857599905);
        it.putExtra("BOT_location03_lat",120.48703868390396);
        it.putExtra("BOT_location03_name","屏東地政事務所");

        it.putExtra("BOT_location04_lng",22.6659254610116);
        it.putExtra("BOT_location04_lat",120.50483176742488);
        it.putExtra("BOT_location04_name","國立屏東大學民生校區");

        it.putExtra("BOT_location05_lng",22.658146885610336);
        it.putExtra("BOT_location05_lat",120.51157614291307);
        it.putExtra("BOT_location05_name","國立屏東大學屏商校區");

        it.putExtra("LBOT_location01_lng",22.670909781271323);
        it.putExtra("LBOT_location01_lat",120.48693319739638);
        it.putExtra("LBOT_location01_name","臺灣土地銀行(屏東分行)");

        it.putExtra("LBOT_location02_lng",22.662255400010995);
        it.putExtra("LBOT_location02_lat",120.4923686084989);
        it.putExtra("LBOT_location02_name","台糖冰店");

        it.putExtra("LBOT_location03_lng",22.67526498555105);
        it.putExtra("LBOT_location03_lat",120.48759418406418);
        it.putExtra("LBOT_location03_name","屏東水利會");

        it.putExtra("LBOT_location04_lng",22.673551977726984);
        it.putExtra("LBOT_location04_lat", 120.49557395506804);
        it.putExtra("LBOT_location04_name","屏東醫院");

        it.putExtra("LBOT_location05_lng",22.68403916030711);
        it.putExtra("LBOT_location05_lat",120.48701709924715);
        it.putExtra("LBOT_location05_name","迪卡儂屏東店");

        it.putExtra("TCB_location01_lng",22.674471076200554);
        it.putExtra("TCB_location01_lat",120.48982409739664);
        it.putExtra("TCB_location01_name","太平洋百貨公司(屏東店)");

        it.putExtra("TCB_location02_lng",22.67861746660202);
        it.putExtra("TCB_location02_lat",120.47050225506813);
        it.putExtra("TCB_location02_name","空軍基地");

        it.putExtra("TCB_location03_lng",22.673039777202984);
        it.putExtra("TCB_location03_lat", 120.48920193972505);
        it.putExtra("TCB_location03_name","合作金庫商業銀行(屏東分行)");

        it.putExtra("TCB_location04_lng",22.670264583109923);
        it.putExtra("TCB_location04_lat",120.49178726856073);
        it.putExtra("TCB_location04_name","合作金庫商業銀行(屏南分行)");

        it.putExtra("TCB_location05_lng",22.68220489603158);
        it.putExtra("TCB_location05_lat",120.50262333861727);
        it.putExtra("TCB_location05_name","基督教醫院");

        it.putExtra("TCB_location06_lng",22.68096565056825);
        it.putExtra("TCB_location06_lat",120.50527689402388);
        it.putExtra("TCB_location06_name","基督教醫院瑞光分院");

        it.putExtra("FCB_location01_lng",22.67134178155185);
        it.putExtra("FCB_location01_lat",120.48973575506811);
        it.putExtra("FCB_location01_name","第一商業銀行(屏東分行)");

        it.putExtra("FCB_location02_lng",22.643244870380666);
        it.putExtra("FCB_location02_lat",120.61088335977327);
        it.putExtra("FCB_location02_name","屏東科大圖書館");

        it.putExtra("HNCB_location01_lng",22.657931454266986);
        it.putExtra("HNCB_location01_lat",120.48622313111011);
        it.putExtra("HNCB_location01_name","中華電信(屏東營運處)");

        it.putExtra("HNCB_location02_lng",22.67431579496401);
        it.putExtra("HNCB_location02_lat",120.48976214444606);
        it.putExtra("HNCB_location02_name","豐屏實業(SOGO)");

        it.putExtra("HNCB_location03_lng",22.670220723566874);
        it.putExtra("HNCB_location03_lat",120.48944908201926);
        it.putExtra("HNCB_location03_name","華南商業銀行(屏東分行)");

        it.putExtra("CHCB_location01_lng",22.66061502186262);
        it.putExtra("CHCB_location01_lat", 120.46123437736215);
        it.putExtra("CHCB_location01_name","宏森木業公司");

        it.putExtra("CHCB_location02_lng",22.67392887540553);
        it.putExtra("CHCB_location02_lat",120.4891621820534);
        it.putExtra("CHCB_location02_name","彰化商業銀行(屏東分行)");

        it.putExtra("CHCB_location03_lng",22.654686213240982);
        it.putExtra("CHCB_location03_lat",120.49781761273947);
        it.putExtra("CHCB_location03_name","屏東縣政府警察局交通隊");

        it.putExtra("TSCSB_location01_lng",22.684951650851406);
        it.putExtra("TSCSB_location01_lat", 120.49437621273984);
        it.putExtra("TSCSB_location01_name","上海商業儲蓄銀行(屏東分行)");

        it.putExtra("TFCB_location01_lng",22.673825429779562);
        it.putExtra("TFCB_location01_lat", 120.49197481111119);
        it.putExtra("TFCB_location01_name","台北富邦商業銀行(屏東分行)");

        it.putExtra("TFCB_location02_lng",22.67332620610816);
        it.putExtra("TFCB_location02_lat", 120.49338986778773);
        it.putExtra("TFCB_location02_name","環球購物中心屏東店");

        it.putExtra("CUB_location01_lng",22.681557350397615);
        it.putExtra("CUB_location01_lat", 120.47484332349308);
        it.putExtra("CUB_location01_name","全家超商(屏東大崇店)");

        it.putExtra("CUB_location02_lng",22.635746213447895);
        it.putExtra("CUB_location02_lat", 120.48222126334356);
        it.putExtra("CUB_location02_name","全家超商(屏東公館店)");

        it.putExtra("CUB_location03_lng",22.665428841036377);
        it.putExtra("CUB_location03_lat", 120.50168857606124);
        it.putExtra("CUB_location03_name","全家超商(屏東民榮店)");

        it.putExtra("CUB_location04_lng",22.660430587517045);
        it.putExtra("CUB_location04_lat", 120.51321040197605);
        it.putExtra("CUB_location04_name","全家超商(屏東安心店)");

        it.putExtra("CUB_location05_lng",22.656139329405406);
        it.putExtra("CUB_location05_lat", 120.47686407178291);
        it.putExtra("CUB_location05_name","全家超商(屏東武成店)");

        it.putExtra("CUB_location06_lng",22.657233579239414);
        it.putExtra("CUB_location06_lat", 120.51089121736302);
        it.putExtra("CUB_location06_name","全家超商(屏東商大店)");

        it.putExtra("CUB_location07_lng",22.682534383144752);
        it.putExtra("CUB_location07_lat", 120.50209952103442);
        it.putExtra("CUB_location07_name","全家超商(屏東華敬店)");

        it.putExtra("CUB_location08_lng",22.6737278205107);
        it.putExtra("CUB_location08_lat", 120.4920764023746);
        it.putExtra("CUB_location08_name","全家超商(屏東愛園店)");

        it.putExtra("CUB_location09_lng",22.67389169840016);
        it.putExtra("CUB_location09_lat", 120.51082469650821);
        it.putExtra("CUB_location09_name","全家超商(屏東瑞光店)");

        it.putExtra("CUB_location10_lng",22.68704115145951);
        it.putExtra("CUB_location10_lat", 120.49446751273993);
        it.putExtra("CUB_location10_name","全家超商(屏東廣興店)");

        it.putExtra("CUB_location11_lng",22.655491388682087);
        it.putExtra("CUB_location11_lat", 120.50448658765323);
        it.putExtra("CUB_location11_name","全家超商(屏東歸來店)");

        it.putExtra("CUB_location12_lng",22.67359157640073);
        it.putExtra("CUB_location12_lat", 120.49557395506804);
        it.putExtra("CUB_location12_name","全家超商(屏東醫院店)");

        it.putExtra("CUB_location13_lng",22.671568480681245);
        it.putExtra("CUB_location13_lat", 120.48083353972493);
        it.putExtra("CUB_location13_name","全聯(屏東民族)");

        it.putExtra("CUB_location14_lng",22.683088158883805);
        it.putExtra("CUB_location14_lat", 120.50522818205361);
        it.putExtra("CUB_location14_name","全聯(屏東大連)");

        it.putExtra("CUB_location15_lng",22.68701175023167);
        it.putExtra("CUB_location15_lat", 120.496503368561);
        it.putExtra("CUB_location15_name","全聯(屏東中正)");

        it.putExtra("CUB_location16_lng",22.661301300927235);
        it.putExtra("CUB_location16_lat", 120.47901091273934);
        it.putExtra("CUB_location16_name","全聯(屏東永大)");

        it.putExtra("CUB_location17_lng",22.683480155065944);
        it.putExtra("CUB_location17_lat", 120.48588840109754);
        it.putExtra("CUB_location17_name","全聯(屏東自由)");

        it.putExtra("CUB_location18_lng",22.66860031059648);
        it.putExtra("CUB_location18_lat", 120.49426493745618);
        it.putExtra("CUB_location18_name","全聯(屏東林森)");

        it.putExtra("CUB_location19_lng",22.677036169123234);
        it.putExtra("CUB_location19_lat", 120.49901252438187);
        it.putExtra("CUB_location19_name","全聯(屏東勝利)");

        it.putExtra("CUB_location20_lng",22.673578539073475);
        it.putExtra("CUB_location20_lat", 120.49307645789533);
        it.putExtra("CUB_location20_name","全聯(屏東復興南)");

        it.putExtra("CUB_location21_lng",22.650934717907496);
        it.putExtra("CUB_location21_lat", 120.48611139739612);
        it.putExtra("CUB_location21_name","全聯(屏東瑞民)");

        it.putExtra("CUB_location22_lng",22.687639475524527);
        it.putExtra("CUB_location22_lat", 120.4832160459515);
        it.putExtra("CUB_location22_name","全聯(屏東廣東)");

        it.putExtra("CUB_location23_lng",22.675503873124793);
        it.putExtra("CUB_location23_lat", 120.48966205506811);
        it.putExtra("CUB_location23_name","國泰世華商業銀行(屏東分行)");

        it.putExtra("CUB_location24_lng",22.677183859397292);
        it.putExtra("CUB_location24_lat", 120.49071885373165);
        it.putExtra("CUB_location24_name","國壽屏東中正大樓");

        it.putExtra("CUB_location25_lng",22.679181725673125);
        it.putExtra("CUB_location25_lat", 120.50618205652424);
        it.putExtra("CUB_location25_name","萊爾富(屏東好豐年店)");

        it.putExtra("CUB_location26_lng",22.688833746677616);
        it.putExtra("CUB_location26_lat", 120.47537455321795);
        it.putExtra("CUB_location26_name","萊爾富(屏東崇朝店)");

        it.putExtra("CUB_location27_lng",22.666659429820374);
        it.putExtra("CUB_location27_lat", 120.50632712902657);
        it.putExtra("CUB_location27_name","萊爾富超商(屏東大學店)");

        it.putExtra("CUB_location28_lng",22.669139385087284);
        it.putExtra("CUB_location28_lat", 120.48638788205335);
        it.putExtra("CUB_location28_name","萊爾富超商(屏東太陽城店)");

        it.putExtra("CUB_location29_lng",22.683621155807238);
        it.putExtra("CUB_location29_lat", 120.50183352623247);
        it.putExtra("CUB_location29_name","萊爾富超商(屏東屏敬店)");

        it.putExtra("CUB_location30_lng",22.664970531603633);
        it.putExtra("CUB_location30_lat", 120.48135683638523);
        it.putExtra("CUB_location30_name","萊爾富超商(屏東建欣店)");

        it.putExtra("BOK_location01_lng",22.677140209165913);
        it.putExtra("BOK_location01_lat", 120.49061273081716);
        it.putExtra("BOK_location01_name","高雄銀行(屏東分行)");

        it.putExtra("MICB_location01_lng",22.648470381935113);
        it.putExtra("MICB_location01_lat", 120.45041919302001);
        it.putExtra("MICB_location01_name","台灣本田(股)公司");

        it.putExtra("MICB_location02_lng",22.67038183960202);
        it.putExtra("MICB_location02_lat",120.4843735400066);
        it.putExtra("MICB_location02_name","兆豐國際商業銀行(屏東分行)");

        it.putExtra("MICB_location03_lng",22.682145347470037);
        it.putExtra("MICB_location03_lat",120.50262094512915);
        it.putExtra("MICB_location03_name","基督教醫院");

        it.putExtra("TBB_location01_lng",22.695446529994065);
        it.putExtra("TBB_location01_lat", 120.49260431088973);
        it.putExtra("TBB_location01_name","台電屏東區營業處");

        it.putExtra("TBB_location02_lng",22.67042237926437);
        it.putExtra("TBB_location02_lat",120.48993218975548);
        it.putExtra("TBB_location02_name","台灣中小企業銀行(屏東分行)");

        it.putExtra("TBB_location03_lng",22.64214563559926);
        it.putExtra("TBB_location03_lat",120.45411769841373);
        it.putExtra("TBB_location03_name","屏東加工出口區");

        it.putExtra("SKCB_location01_lng",22.65209833727142);
        it.putExtra("SKCB_location01_lat", 120.48432466819303);
        it.putExtra("SKCB_location01_name","和生大樓");

        it.putExtra("SKCB_location02_lng",22.67177575470546);
        it.putExtra("SKCB_location02_lat", 120.4990783543605);
        it.putExtra("SKCB_location02_name","新光商業銀行(東園分行)");

        it.putExtra("SKCB_location03_lng",22.67466245753273);
        it.putExtra("SKCB_location03_lat", 120.48954100867242);
        it.putExtra("SKCB_location03_name","新光商業銀行(屏東分行)");

        it.putExtra("SKCB_location04_lng",22.682748466899408);
        it.putExtra("SKCB_location04_lat", 120.49796401490339);
        it.putExtra("SKCB_location04_name","家樂福(屏東店)");

        it.putExtra("SKCB_location05_lng",22.663364504576307);
        it.putExtra("SKCB_location05_lat", 120.48905727002177);
        it.putExtra("SKCB_location05_name","家樂福(新屏店)");

        it.putExtra("SKCB_location06_lng",22.669889871413968);
        it.putExtra("SKCB_location06_lat", 120.49456081533094);
        it.putExtra("SKCB_location06_name","新壽屏東大樓");

        it.putExtra("SB_location01_lng",22.673566974453657);
        it.putExtra("SB_location01_lat", 120.48946714615504);
        it.putExtra("SB_location01_name","陽信商業銀行(屏東分行)");

        it.putExtra("SB_location02_lng",22.68123757522102);
        it.putExtra("SB_location02_lat", 120.4844245832959);
        it.putExtra("SB_location02_name","寶建醫院");

        it.putExtra("CCB_location01_lng",22.66898008094611);
        it.putExtra("CCB_location01_lat", 120.4860747160005);
        it.putExtra("CCB_location01_name","屏東火車站");

        it.putExtra("UBOT_location01_lng",22.65757521564613);
        it.putExtra("UBOT_location01_lat",120.48421567866328);
        it.putExtra("UBOT_location01_name","OK超商屏東棒球店");

        it.putExtra("UBOT_location02_lng",22.67326107215056);
        it.putExtra("UBOT_location02_lat",120.49126513230961);
        it.putExtra("UBOT_location02_name","小北百貨林森店");

        it.putExtra("UBOT_location03_lng",22.672848886444395);
        it.putExtra("UBOT_location03_lat", 120.48382584339072);
        it.putExtra("UBOT_location03_name","小北百貨重慶店");

        it.putExtra("UBOT_location04_lng",22.69089931030156);
        it.putExtra("UBOT_location04_lat",120.50078154260115);
        it.putExtra("UBOT_location04_name","小北百貨莊敬店");

        it.putExtra("UBOT_location05_lng",22.68776623960225);
        it.putExtra("UBOT_location05_lat",120.48913116961965);
        it.putExtra("UBOT_location05_name","小北百貨廣東店");

        it.putExtra("UBOT_location06_lng",22.670892620678902);
        it.putExtra("UBOT_location06_lat",120.48314057160981);
        it.putExtra("UBOT_location06_name","聯邦商業銀行(屏東分行)");

        it.putExtra("YCB_location01_lng",22.670042912697856);
        it.putExtra("YCB_location01_lat", 120.49179737941634);
        it.putExtra("YCB_location01_name","屏東民生收付處");

        it.putExtra("YCB_location02_lng",22.677073967748882);
        it.putExtra("YCB_location02_lat", 120.49096076638457);
        it.putExtra("YCB_location02_name","屏南收付處");

        it.putExtra("YCB_location03_lng",22.67409252782302);
        it.putExtra("YCB_location03_lat", 120.49085994280782);
        it.putExtra("YCB_location03_name","太平洋百貨屏東二館");

        it.putExtra("YCB_location04_lng",22.674103821589682);
        it.putExtra("YCB_location04_lat", 120.48969579982638);
        it.putExtra("YCB_location04_name","太平洋百貨屏東店");

        it.putExtra("YCB_location05_lng",22.685467451432835);
        it.putExtra("YCB_location05_lat", 120.4963501266219);
        it.putExtra("YCB_location05_name","元大商業銀行(屏東分行)");

        it.putExtra("YCB_location06_lng",22.67327411153368);
        it.putExtra("YCB_location06_lat", 120.49954483352367);
        it.putExtra("YCB_location06_name","元大商業銀行(屏榮分行)");

        it.putExtra("YCB_location07_lng",22.66107254048044);
        it.putExtra("YCB_location07_lat", 120.51016263454196);
        it.putExtra("YCB_location07_name","特力屋屏東店");

        it.putExtra("BS_location01_lng",22.671624768704522);
        it.putExtra("BS_location01_lat", 120.4891018860563);
        it.putExtra("BS_location01_name","永豐商業銀行(屏東分行)");

        it.putExtra("BS_location02_lng",22.681612668201538);
        it.putExtra("BS_location02_lat", 120.48479452715463);
        it.putExtra("BS_location02_name","萊爾富(迪化店)");

        it.putExtra("ESCB_location01_lng",22.66896666142207);
        it.putExtra("ESCB_location01_lat", 120.48606625555982);
        it.putExtra("ESCB_location01_name","全家(車頭店)");

        it.putExtra("ESCB_location02_lng",22.6713221748485);
        it.putExtra("ESCB_location02_lat", 120.48713520677133);
        it.putExtra("ESCB_location02_name","玉山商業銀行(屏東分行)");

        it.putExtra("ESCB_location03_lng",22.68340471751479);
        it.putExtra("ESCB_location03_lat", 120.48296805338217);
        it.putExtra("ESCB_location03_name","家樂福屏東自由店");

        it.putExtra("ESCB_location04_lng",22.682981474049292);
        it.putExtra("ESCB_location04_lat", 120.49795338205357);
        it.putExtra("ESCB_location04_name","家樂福屏東店");

        it.putExtra("ESCB_location05_lng",22.663360085885902);
        it.putExtra("ESCB_location05_lat", 120.4890579958679);
        it.putExtra("ESCB_location05_name","家樂福新屏店");

        it.putExtra("KGB_location01_lng",22.68272502172001);
        it.putExtra("KGB_location01_lat", 120.49910871383145);
        it.putExtra("KGB_location01_name","凱基商業銀行(屏東分行)");

        it.putExtra("TIB_location01_lng",22.664931604915417);
        it.putExtra("TIB_location01_lat", 120.47262528083529);
        it.putExtra("TIB_location01_name","OK超商(屏東建國)");

        it.putExtra("TIB_location02_lng",22.676484676166286);
        it.putExtra("TIB_location02_lat", 120.5035367840075);
        it.putExtra("TIB_location02_name","OK超商(屏東建豐)");

        it.putExtra("TIB_location03_lng",22.670832519145986);
        it.putExtra("TIB_location03_lat", 120.48125852942844);
        it.putExtra("TIB_location03_name","全家超商(屏東大埔店)");

        it.putExtra("TIB_location04_lng",22.68246359962539);
        it.putExtra("TIB_location04_lat", 120.49310034051798);
        it.putExtra("TIB_location04_name","全家超商(屏東中華店)");

        it.putExtra("TIB_location05_lng",22.68886207553874);
        it.putExtra("TIB_location05_lat", 120.49850386954962);
        it.putExtra("TIB_location05_name","全家超商(屏東正裕店)");

        it.putExtra("TIB_location06_lng",22.671190316630224);
        it.putExtra("TIB_location06_lat", 120.49816931055742);
        it.putExtra("TIB_location06_name","全家超商(屏東民享店)");

        it.putExtra("TIB_location07_lng",22.66713118724454);
        it.putExtra("TIB_location07_lat", 120.48107331117905);
        it.putExtra("TIB_location07_name","全家超商(屏東昌賢店)");

        it.putExtra("TIB_location08_lng",22.66903330603919);
        it.putExtra("TIB_location08_lat", 120.47446464711963);
        it.putExtra("TIB_location08_name","全家超商(屏東長安店)");

        it.putExtra("TIB_location09_lng",22.673909181967996);
        it.putExtra("TIB_location09_lat", 120.5014401062428);
        it.putExtra("TIB_location09_name","全家超商(屏東屏榮店)");

        it.putExtra("TIB_location10_lng",22.678940540255756);
        it.putExtra("TIB_location10_lat", 120.47956726371707);
        it.putExtra("TIB_location10_name","全家超商(屏東徐州店)");

        it.putExtra("TIB_location11_lng",22.68756242003715);
        it.putExtra("TIB_location11_lat", 120.48615497462069);
        it.putExtra("TIB_location11_name","全家超商(屏東勝利店)");

        it.putExtra("TIB_location12_lng",22.661342023794077);
        it.putExtra("TIB_location12_lat", 120.47908989384162);
        it.putExtra("TIB_location12_name","全家超商(屏東華盛店)");

        it.putExtra("TIB_location13_lng",22.664654043480976);
        it.putExtra("TIB_location13_lat", 120.49114348389467);
        it.putExtra("TIB_location13_name","全家超商(屏東福光店)");

        it.putExtra("TIB_location14_lng",22.68022120051589);
        it.putExtra("TIB_location14_lat", 120.50185776619308);
        it.putExtra("TIB_location14_name","全家超商(屏東豐榮店)");

        it.putExtra("TIB_location15_lng",22.672921847235568);
        it.putExtra("TIB_location15_lat", 120.49944967099063);
        it.putExtra("TIB_location15_name","台新國際商業銀行(屏東分行)");

        it.putExtra("TIB_location16_lng",22.683619367478244);
        it.putExtra("TIB_location16_lat", 120.48605775319956);
        it.putExtra("TIB_location16_name","丁丁屏東自由");

        it.putExtra("TIB_location17_lng",22.68857516996056);
        it.putExtra("TIB_location17_lat", 120.49463687298676);
        it.putExtra("TIB_location17_name","屏東國稅局");

        it.putExtra("TIB_location18_lng",22.673344427370363);
        it.putExtra("TIB_location18_lat", 120.49340005948333);
        it.putExtra("TIB_location18_name","屏東環球購物中心");

        it.putExtra("JICB_location01_lng",22.6729552316745);
        it.putExtra("JICB_location01_lat", 120.49322330264197);
        it.putExtra("JICB_location01_name","日盛國際商業銀行(屏東分行)");

        it.putExtra("CTBC_location01_lng",22.683065429973762);
        it.putExtra("CTBC_location01_lat", 120.49194755340943);
        it.putExtra("CTBC_location01_name","中國信託商業銀行(屏東分行)");

        it.putExtra("CTBC_location02_lng",22.673287810840442);
        it.putExtra("CTBC_location02_lat", 120.49339918257253);
        it.putExtra("CTBC_location02_name","屏東環球購物中心");

        it.putExtra("CTBC_location03_lng",22.635912522955373);
        it.putExtra("CTBC_location03_lat", 120.48270523296789);
        it.putExtra("CTBC_location03_name","(7-ELEVEN公華門市)");

        it.putExtra("CTBC_location04_lng",22.687915257644903);
        it.putExtra("CTBC_location04_lat", 120.49084628552527);
        it.putExtra("CTBC_location04_name","(7-ELEVEN公裕門市)");

        it.putExtra("CTBC_location05_lng",22.65734403649461);
        it.putExtra("CTBC_location05_lat", 120.47635910937952);
        it.putExtra("CTBC_location05_name","(7-ELEVEN永大門市)");

        it.putExtra("CTBC_location06_lng",22.663151603385337);
        it.putExtra("CTBC_location06_lat", 120.48778145593222);
        it.putExtra("CTBC_location06_name","(7-ELEVEN永發門市)");

        it.putExtra("CTBC_location07_lng",22.672283803937646);
        it.putExtra("CTBC_location07_lat", 120.48346378941135);
        it.putExtra("CTBC_location07_name","(7-ELEVEN光榮門市)");

        it.putExtra("CTBC_location08_lng",22.67879908714883);
        it.putExtra("CTBC_location08_lat", 120.4748997326789);
        it.putExtra("CTBC_location08_name","(7-ELEVEN同勝門市)");

        it.putExtra("CTBC_location09_lng",22.699650304973698);
        it.putExtra("CTBC_location09_lat", 120.50939732949554);
        it.putExtra("CTBC_location09_name","(7-ELEVEN合家宜門市)");

        it.putExtra("CTBC_location10_lng",22.651982057056394);
        it.putExtra("CTBC_location10_lat", 120.48613655319954);
        it.putExtra("CTBC_location10_name","(7-ELEVEN和興門市)");

        it.putExtra("CTBC_location11_lng",22.686394091315663);
        it.putExtra("CTBC_location11_lat", 120.47180434048342);
        it.putExtra("CTBC_location11_name","(7-ELEVEN東山河門市");

        it.putExtra("CTBC_location12_lng",22.683344743337706);
        it.putExtra("CTBC_location12_lat", 120.48093972101303);
        it.putExtra("CTBC_location12_name","(7-ELEVEN欣欣鼎門市");

        it.putExtra("CTBC_location13_lng",22.669805359829873);
        it.putExtra("CTBC_location13_lat", 120.49203016762459);
        it.putExtra("CTBC_location13_name","(7-ELEVEN金樹門市");

        it.putExtra("CTBC_location14_lng",22.67735090817877);
        it.putExtra("CTBC_location14_lat", 120.52725834499272);
        it.putExtra("CTBC_location14_name","(7-ELEVEN長治門市");

        it.putExtra("CTBC_location15_lng",22.665511124803274);
        it.putExtra("CTBC_location15_lat", 120.4788854136129);
        it.putExtra("CTBC_location15_name","(7-ELEVEN厚生門市");

        it.putExtra("CTBC_location16_lng",22.662192406452313);
        it.putExtra("CTBC_location16_lat", 120.5071339863038);
        it.putExtra("CTBC_location16_name","(7-ELEVEN屏生門市");

        it.putExtra("CTBC_location17_lng",22.64098463607612);
        it.putExtra("CTBC_location17_lat", 120.59536980629461);
        it.putExtra("CTBC_location17_name","(7-ELEVEN屏科大門市");

        it.putExtra("CTBC_location18_lng",22.669451192205024);
        it.putExtra("CTBC_location18_lat", 120.48575091055048);
        it.putExtra("CTBC_location18_name","(7-ELEVEN屏棧門市");

        it.putExtra("CTBC_location19_lng",22.664578958566665);
        it.putExtra("CTBC_location19_lat", 120.51163592419684);
        it.putExtra("CTBC_location19_name","(7-ELEVEN香楊門市");

        it.putExtra("CTBC_location20_lng",22.669869177715928);
        it.putExtra("CTBC_location20_lat", 120.48596796082914);
        it.putExtra("CTBC_location20_name","(7-ELEVEN國站門市");

        it.putExtra("CTBC_location21_lng",22.662852601450275);
        it.putExtra("CTBC_location21_lat", 120.4787196457227);
        it.putExtra("CTBC_location21_name","(7-ELEVEN崇武門市");

        it.putExtra("CTBC_location22_lng",22.68281584005043);
        it.putExtra("CTBC_location22_lat", 120.47491407473288);
        it.putExtra("CTBC_location22_name","(7-ELEVEN崇蘭門市");

        it.putExtra("CTBC_location23_lng",22.65587059497434);
        it.putExtra("CTBC_location23_lat", 120.4817687712073);
        it.putExtra("CTBC_location23_name","(7-ELEVEN統棒門市");

        it.putExtra("CTBC_location24_lng",22.687388160526375);
        it.putExtra("CTBC_location24_lat", 120.48093842358239);
        it.putExtra("CTBC_location24_name","(7-ELEVEN翊聖門市)");

        it.putExtra("CTBC_location25_lng",22.679218846259065);
        it.putExtra("CTBC_location25_lat", 120.48100122228426);
        it.putExtra("CTBC_location25_name","(7-ELEVEN博勝門市)");

        it.putExtra("CTBC_location26_lng",22.647507457951193);
        it.putExtra("CTBC_location26_lat", 120.48566774813536);
        it.putExtra("CTBC_location26_name","(7-ELEVEN堡勤門市)");

        it.putExtra("CTBC_location27_lng",22.686925185499558);
        it.putExtra("CTBC_location27_lat", 120.5035404676801);
        it.putExtra("CTBC_location27_name","(7-ELEVEN華敬門市)");

        it.putExtra("CTBC_location28_lng",22.660173811302453);
        it.putExtra("CTBC_location28_lat", 120.47296563057735);
        it.putExtra("CTBC_location28_name","(7-ELEVEN愛買門市)");

        it.putExtra("CTBC_location29_lng",22.683483847404837);
        it.putExtra("CTBC_location29_lat", 120.49050063128978);
        it.putExtra("CTBC_location29_name","(7-ELEVEN新自孝門市)");

        it.putExtra("CTBC_location30_lng",22.683719772805095);
        it.putExtra("CTBC_location30_lat", 120.50797584742338);
        it.putExtra("CTBC_location30_name","(7-ELEVEN新德慧門市)");

        it.putExtra("CTBC_location31_lng",22.673973964793195);
        it.putExtra("CTBC_location31_lat", 120.4916273698137);
        it.putExtra("CTBC_location31_name","(7-ELEVEN新樂興門市)");

        it.putExtra("CTBC_location32_lng",22.671949414921066);
        it.putExtra("CTBC_location32_lat", 120.49936398039637);
        it.putExtra("CTBC_location32_name","(7-ELEVEN新豐榮門市)");

        it.putExtra("CTBC_location33_lng",22.66011987190286);
        it.putExtra("CTBC_location33_lat", 120.51119670223255);
        it.putExtra("CTBC_location33_name","(7-ELEVEN瑞光門市)");

        it.putExtra("CTBC_location34_lng",22.66470823436928);
        it.putExtra("CTBC_location34_lat", 120.490957372743);
        it.putExtra("CTBC_location34_name","(7-ELEVEN瑞廣門市)");

        it.putExtra("CTBC_location35_lng",22.683526076788958);
        it.putExtra("CTBC_location35_lat", 120.4981167440693);
        it.putExtra("CTBC_location35_name","(7-ELEVEN廣大門市)");

        it.putExtra("CTBC_location36_lng",22.68941568563677);
        it.putExtra("CTBC_location36_lat", 120.49890450922673);
        it.putExtra("CTBC_location36_name","(7-ELEVEN廣正門市)");

        it.putExtra("CTBC_location37_lng",22.68753296249414);
        it.putExtra("CTBC_location37_lat", 120.48498680387996);
        it.putExtra("CTBC_location37_name","(7-ELEVEN廣吉門市)");

        it.putExtra("CTBC_location38_lng",22.6772082437626);
        it.putExtra("CTBC_location38_lat", 120.50100796703653);
        it.putExtra("CTBC_location38_name","(7-ELEVEN廣豐門市)");

        it.putExtra("CTBC_location39_lng",22.6790192983541);
        it.putExtra("CTBC_location39_lat", 120.47788286097985);
        it.putExtra("CTBC_location39_name","(7-ELEVEN潭墘門市)");

        it.putExtra("CTBC_location40_lng",22.65083930607195);
        it.putExtra("CTBC_location40_lat", 120.46431550687515);
        it.putExtra("CTBC_location40_name","(7-ELEVEN頭前溪門市)");

        it.putExtra("CTBC_location41_lng",22.676111617810932);
        it.putExtra("CTBC_location41_lat", 120.50891335560762);
        it.putExtra("CTBC_location41_name","(7-ELEVEN薇豐門市)");

        it.putExtra("CTBC_location42_lng",22.659591546046993);
        it.putExtra("CTBC_location42_lat", 120.50561796369915);
        it.putExtra("CTBC_location42_name","(7-ELEVEN歸來門市)");

        it.putExtra("PTFA_location01_lng",22.666705299884043);
        it.putExtra("PTFA_location01_lat", 120.49900647610627);
        it.putExtra("PTFA_location01_name","屏東市農會");

        it.putExtra("CWP_location01_lng",22.664766503697788);
        it.putExtra("CWP_location01_lat", 120.47606630947874);
        it.putExtra("CWP_location01_name","大武營區");

        it.putExtra("CWP_location02_lng",22.65510813084213);
        it.putExtra("CWP_location02_lat", 120.46649463540848);
        it.putExtra("CWP_location02_name","水利局第七區工程處");

        it.putExtra("CWP_location03_lng",22.670936439260352);
        it.putExtra("CWP_location03_lat", 120.48258572751467);
        it.putExtra("CWP_location03_name","大埔郵局");

        it.putExtra("CWP_location04_lng",22.68217959241689);
        it.putExtra("CWP_location04_lat", 120.49177439982641);
        it.putExtra("CWP_location04_name","中正路郵局");

        it.putExtra("CWP_location05_lng",22.669417749004992);
        it.putExtra("CWP_location05_lat", 120.47658958111248);
        it.putExtra("CWP_location05_name","六塊厝郵局");

        it.putExtra("CWP_location06_lng",22.638091538703495);
        it.putExtra("CWP_location06_lat", 120.48507365992941);
        it.putExtra("CWP_location06_name","公館郵局");

        it.putExtra("CWP_location07_lng",22.668929006032396);
        it.putExtra("CWP_location07_lat", 120.486063588672);
        it.putExtra("CWP_location07_name","屏東火車站");

        it.putExtra("CWP_location08_lng",22.67582576669308);
        it.putExtra("CWP_location08_lat", 120.4888520397058);
        it.putExtra("CWP_location08_name","北平路郵局");

        it.putExtra("CWP_location09_lng",22.670514087597322);
        it.putExtra("CWP_location09_lat", 120.49160368533813);
        it.putExtra("CWP_location09_name","民生路郵局");

        it.putExtra("CWP_location10_lng",22.655810026408794);
        it.putExtra("CWP_location10_lat", 120.4815145750592);
        it.putExtra("CWP_location10_name","永安郵局");

        it.putExtra("CWP_location11_lng",22.67145744512256);
        it.putExtra("CWP_location11_lat", 120.49776197690295);
        it.putExtra("CWP_location11_name","林森路郵局");

        it.putExtra("CWP_location12_lng",22.656482632644003);
        it.putExtra("CWP_location12_lat", 120.48335824374273);
        it.putExtra("CWP_location12_name","屏東法院");

        it.putExtra("CWP_location13_lng",22.665454954310448);
        it.putExtra("CWP_location13_lat", 120.47896456855567);
        it.putExtra("CWP_location13_name","厚生郵局");

        it.putExtra("CWP_location14_lng",22.700886040300013);
        it.putExtra("CWP_location14_lat", 120.5108602247092);
        it.putExtra("CWP_location14_name","海豐郵局");

        it.putExtra("CWP_location15_lng",22.686654335556256);
        it.putExtra("CWP_location15_lat", 120.48764144857546);
        it.putExtra("CWP_location15_name","屏東高中");

        it.putExtra("CWP_location16_lng",22.68774252688409);
        it.putExtra("CWP_location16_lat", 120.48539028293297);
        it.putExtra("CWP_location16_name","崇蘭郵局");

        it.putExtra("CWP_location17_lng",22.669724944804816);
        it.putExtra("CWP_location17_lat", 120.5018272807263);
        it.putExtra("CWP_location17_name","屏東大學林森校區");

        it.putExtra("CWP_location18_lng",22.679353635202315);
        it.putExtra("CWP_location18_lat", 120.48593590293314);
        it.putExtra("CWP_location18_name","勝利路郵局");

        it.putExtra("CWP_location19_lng",22.656826112435567);
        it.putExtra("CWP_location19_lat", 120.5165403040099);
        it.putExtra("CWP_location19_name","屏東郵局郵務大樓");

        it.putExtra("CWP_location20_lng",22.692819067680325);
        it.putExtra("CWP_location20_lat", 120.48900699504655);
        it.putExtra("CWP_location20_name","屏東監理站");

        it.putExtra("CWP_location21_lng",22.680448986096735);
        it.putExtra("CWP_location21_lat", 120.50179798902879);
        it.putExtra("CWP_location21_name","廣東路郵局");

        it.putExtra("CWP_location22_lng",22.648460241878645);
        it.putExtra("CWP_location22_lat", 120.46473725642012);
        it.putExtra("CWP_location22_name","頭前溪郵局");

        it.putExtra("CWP_location23_lng",22.664285466110645);
        it.putExtra("CWP_location23_lat", 120.50339252040841);
        it.putExtra("CWP_location23_name","歸來郵局");
        startActivity(it);
    }

    public void Maker(View v)
    {
        Intent it = new Intent(this,Maker.class);
        startActivity(it);
    }
}
