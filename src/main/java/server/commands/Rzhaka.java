package server.commands;

import server.dao.RouteDAO;
import common.interaction.Response;
import common.interaction.Status;

/**
 * Класс команды RZHAKA, предназначенный для мемного троллинга
 */
public class Rzhaka extends ACommands{

//    public String execute(RouteDAO routeDAO) {
//        while (true) {
//            try {
//                File common.file = new File("forAlex.png");
//                BufferedImage bufferedImage = ImageIO.read(common.file);
//
//                ImageIcon imageIcon = new ImageIcon(bufferedImage);
//                JFrame jFrame = new JFrame();
//
//                jFrame.setLayout(new FlowLayout());
//
//                jFrame.setSize(1080, 1080);
//                JLabel jLabel = new JLabel();
//
//                jLabel.setIcon(imageIcon);
//                jFrame.add(jLabel);
//                jFrame.setVisible(true);
//
//                jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//                break;
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//return "vefwerfgw";
//    }
    public Response execute(RouteDAO routeDAO){
        String harosh = "11T1YYY1TYTT11Y11YT1TTYTzftztstsyyy23#5Z5XZZ5XZ$Xkw9wSk$kZ5XE9hhSEk5$kZ5kk$k5ZZ$XXZEhw%AhEwEZXZ5Zk5Z5$ZZPA6E6SE0dmUKGm0MMOMOOOMMDDD&&\n" +
                "TY1TLc[[LlLLlcYY1Y}{]l1T1Y11TTYT11fynF#3a#IV###eaZZZAPZ9qM&HWBW@DDOMq%Z$44#33a$ZX$5kZX$k$Z$X5Z$#4I##ZZ5kkk$ZZ6EPAKbmb0q0pmdq0bOOMOMMM\n" +
                "x]l}{L7[lx]lL7c{}L{7}x]7[c}Lll]Y1YYfyCCJ22u2CuCI4IekO@gQWgQWQH@@DDMMMM8qAZaeI333#V3#e#3VV#44aIa#eV#IaV3#V5$X5X5ASAAwhSh%%9AA%KqGdGGbM\n" +
                "}l[[]7l7lLx{77LL[7{]}Lc[}cc7[}[]]YTTzfJnuJJjC2jojnEWgQWQWRRQND@NNMOM0qmKMMZFjJnn22Fee#VVeVI4e#V3CjuFVIVeV##kk$Z5$9%%Aw9w6AwAwSPhhmU8G\n" +
                "x[c{7[}}]]7l[l7}{c7]]7l{lL[{[c[{}YsjoCntzzzyyfyuu4&WQBHQgBHg&@DDOMMOKGpUqOp2sfzjFCJun34V4e#4#jnF2jjnFueI3#3ea$5k5Z$$SE55Zkh9P%PSwPb0b\n" +
                "clviivlL{}x[[[LL[77ciivivvvviicL71Tzyyszyyfyyyyt#NHWRRRB&@WQNOM&OqUOpUG800OmjfyzfnojCJ4aeFonjuoFFujuJCoF4aIIeaV5XXkZZZXkZAw96hA96%mKp\n" +
                "7iiiivv][LL{l]iiviiiiiiivvvvvvviLlYTzftzysystsfzMBRBgHNODgRRDOMNMGqdmGq9hAmM5TTzfzf2FJCFJCCFCo22n2uC2ooCCJe333eea5X5X5$XkXwE6AE6669q8\n" +
                "iviiiiiv7xlxLivvivvvivvviviiivvvi[[TYztfzfzytzyfBBRWQDKO&QRW&ODNOU00%Kb9Eh8bCTTzyfssfCu2Ctzzofysyjj2FJ2onnuJe#eVa#3kZXXZ5P6hP%hEAw9q8\n" +
                "vvvivvvvivviviivvvvi)/)iivvvivviicLTTzszyyytts118RRBRMOQgBQBNOOOM00UG0KwEmV71YTYTfszssyztsftzyftffssf2juC2jjoo4aVI34kZkX$5hEw%AA6EhGG\n" +
                "vvivviivvvivvivii/\\?)\\(vviivvvvviL[11yttzstzzsY[vT5@BOWBWgWW&MOOdUdpGdpb3T\"\"}TYTTyftsfyssszysftysytzfzsy22JuFFCVIeI3aVXZ$XZPAwwEwS60p\n" +
                "vvviiivvviivvv)(/((|(/|iivviivviv{LY11ytszyzst17iv\\sg@DDNN@&@OGpwE5nT}x/<<>ic111Y1YT1zsftfsstyssy1T11fyzs2uCnCC2FJJ#e43$Xk$wP6AhPP9P0\n" +
                "vvvivi/\\?|))|/(()/||)?)|(iiviiivivix1YYTYT1YT1Y{v(/rn99tYYinOa*<+>+>+++><<rx[{L{[{[Y11TYT1TY1TTY111YTtJV$A%k5#eCCCjJIIVI$ZZXS6SS9%h%9\n" +
                "iviv//?)\\)\\))|?\\||?)))||)()|vvivvi)icTTYT11T1177i)=;\"YkS1rLdM2<>+>>+++<>\"i}}{c]L{l7{7ll}}[lxx}}7x]{Y1t#XZZ$5hSk5$XV3VI3aXk$XE%A6SA99E\n" +
                "\\\\|?(\\//|)()|?|?\\\\??/???)v)(?|\\/(?)/i{YTTTY{7lii\\/*+<<zDPi/Cho\"+>>>+>>>+/[}LivcvvilLx}l{[}c77{]L]l{ctuekZXkX5$XkkX554I34#$XX9EwSPwhhw\n" +
                "/||/?)\\))///))/\\(\\)|??|||?\\)//|/???iic7L[{LL]7i?);\"<+>{DM#{tJ[=;>+>++>+iny{vvvivivvvvvi{L7c}7lc7{7}Lf4aV33VX$kkZZZXZ5VVa4#k$$kw6wAP%E\n" +
                ")(iv)|(?(/(\\)\\=;=\";\"/\\//|/)))\\//ilvvvix{lx[vii(\\;\">(uqRXMSvTe17i?=+>>>>iDBDI7ivvviviviivviivivviiv[]se##44aI55Z5ZX$a#eoV443aIXk$5k$Aw\n" +
                "v]7iv(\\)/(/|))**r=r*r\"\"\";\"**=?v]7{vvivivviivviii{aOBHHOrGMo{Lv\"*>+><>+~~zBWgDpn]viiiiiivivivviiviv7cze#IIIIX5aaVe434un2j2nnVaIeXkZ$Z$\n" +
                "f3aJ1v\\|?||/(?*;\"*r=\"=\"r><=?/viiii?)//vivv1oZbO&gRWWWBN;uS%6nvr;<+><,,``iNBWHBBWMPeylviiiviiivvivilctJV344a3CjJJJjjFCuCuojJ2FjIa#V$XX\n" +
                "XXk#nYv\\?||)?\"r*\"**=\"r+>;(\\vivi\\))*)Y4%ONQgBHQQQHHRggHBSof=;i);>,~_~--  JMBWgWWRgHQRHNMwZoTvviiivi{cyjFuFosyztfsyztfyzsytysonoJCa3aVe\n" +
                "k$5k4jTii//(((r\"=r;\"r><;viivii|/xVMRgBRHggRBBRBHggHHQRNIuT):!_;(;_.`' `iMOHgQHHWWgWQHWRWWQQNO5Flvvv71tsysffYT11TY1TYT11ytzztsf2CnF3e#\n" +
                "55$k5ZkIJtT{v();*;*=<>*ivvvvv|Y@RWWWRgHQRRQWBHggWHHQHRNrv}?vnOHgQML -'iwGMRgHRWQWQBWBQQQHWWRQgHBM5uYYYT11Y1TT1111TTTYY11T11szyyojnFoV\n" +
                "3a3kk$X43###o1v)r;*><>;ivi?i?(SgQQWWQQBRWgWQgWWBHBBRQgBt~\"F&BBgWQu__ ^3M0OBgRBQQBWQHggBBHWRHHHHRQRHDz[YYTLlLcLL{l]c71Y1YTTTT1ytfzznuF\n" +
                "444ea$3#I22jnsxi;rr>:+/vvv|\\(|AWWRQgBHQRgRBQHWBBgWWHQHgG<<hQBgQBH;!' iOMMMWQHQWHgggRWQWWWWRRRRRBHHWg&a[]}7{{c7Lxxl[x7l{]TYYY1YffzsyjJ\n" +
                "Ve##V3aCJuzfyzTi;*\"<<\")vi?(())dHHRWWHRHQgWRgRRHQgHQgRWHRVC&RQHQgRv ';mOMMOQHWRHQRRBBQgQgBgBRRRBWHWHBQB5iivvviviiivcl]}LL}71T1TY1fzfys\n" +
                "JC#VaeJtyyyyYT[v\"*>+=*r|)\\;*\"]&RWWQBWHggHQRWgHWWWBHWWQRHB@WgBHWHRZ'_9OMMM&gggWBWRHRHHQRgWHBWgWBWWRHRWHg$?|iiiivviiiv]l}7][[]T1YYYYTzs\n" +
                "FC2uJfffTY1YYci|;++;=;\";;r*>>tQQHgBBBBQQHgBRHQRQgRHRRWBgWRQQWQRRggJ#OKddO&BgQgWQHQBQgQWHRRBBBHWHRRRWHQBW2**)|?)|\\\\vvivii{[L7cYTT1TYYT\n" +
                "syszy11TL[77Lxv?>>>;*\"*;;\">+>pQBRgWgWBggRBWHgWgRRBRQBgQBQQggWQHQQWDMKGpbOQHQWWHQBBWWWQHgWQQRRQBQRBQRgggWQ2<*r=;*?/|/(ivvii}}[}[cYYYTY\n" +
                "TY11TYc{{7{}]i(<+>\"\"\"rr\";>_ )RgRWRRRHQRHRgWBQBggHHgQNWBggWBRHQBHBRObKmqdOHBHQRRRHHHHHgWWWgRRWgWQgBBBQWHQgH{><;=;**=?/|/iiivilLcl{}T11\n" +
                "1TTc]{iivvvvv*<><;r*;;*;>^'`nBRBWHBHHHBBgQRRgRQQQQRHDHWBgRHQgBgWR@bSmp8ODWHHQRgBQQWQBBQQgWQHBQQQRRQHQBQgHBO?>><\"=;\"=\"\\)(viiivLx77lTTT\n" +
                "}c[Liiivvii)+:+r==*\"\";<>~  -9HBRWgQWQQgWHHWgHggBQQQR@RRHBRQ@NkEO@8wAqbKONRgRHQRBHQRBWHRRWBBWWHBRHgQRBHWQQWg6>>+>>+;=*r/?(|ivii7l{[[1T\n" +
                "[iivi(|/?)?>_=\\?/\"==<>+^`- \\NWWBWWRHWQHWRWBQQWQRRHgQ@D@QN&&@@wMNdKP%UGUMWWHQRgHHHRQQBQRQQRgRWHQRRQHWgRWWHBWH1!+<+<<>rr=|?/))iivvi}{x]\n" +
                "vi|)())((/;+*/((||r;<<+,.'>OWBWHRQgBWRBQWWgRWgRRQBQWONNB@@@D@NOqwP6wqKKMgBgBBQWWBRBRWBWBgHggHBgBWHgHHBWQBQRQ@=^><>++r;;\"(/\\)(iiviv77[\n" +
                "(/?))/?)/(\"<|(\\//|;\"<>>^. egQQWBQRHWRBHWQWHRWHHQWBgQM&NDNN@@@NOOMMU0K8U@ggWgQgWBHgggRQBHgHQggRQHRBBRHWHHgRWHRE>++<<+=\"*;*(\\())/iviivl\n" +
                "|||(|/)(;:_r|iv\\||*\";+~: \"@RgWBRBgRQHWWRQQHRBgWQQQgHMN@g@&DN@WqE9mpUG8MggRgBWWHBRWQgBBHgBWQWRBHgBBRHgRQgBWRgWHZ>:+><>;;\"=;)|\\/|\\viivi\n" +
                "/)?/))()*~+|vii/|?r=r+>_!3gBBWgHWgQBWHWQHgBHRgHRBgQQ&WHHWQW@BBd8dbGp8U&RWHBRWHWQWHggRWBRHQHRWRRRgRRHRHQgRBQBgRgMv++><<+rr==\\??)|vviiv\n" +
                "*r)/)||);++\\iivi(??*==*>iNRX++>vgj>><iQg#v>~_^+?#NH2^<<+vi+,,?$MMIv+`-!+|JNBBl<+>>MM<<<>TQ{+><+GHgHQBWBQRBRgWRBQRZr+>+=\"\"=;\\()(((viiv\n" +
                "=\"\"/)\"r*r;r;/ivv)?|((***4QWB\" `~G;`'`$Wv   +f^'--vQT  --?s-   igj  ..Y*.-.>Og? . 'Ob.`. vg/'`' SRBgWBgRRQBQQWHgBHNv<++;\"=\"*;=(\\)(iviv\n" +
                ">;rr\"\"(?\"\"+,riiiiii||(*/&WHB9~- >`'.*gM,`.-7&+ '.<Hy' . sO ' .|R(-  `Mf - 'VW\\ -''O0 ..'vg|`. .PQRQHgWHHQBWgQRWHB4++++<\"=;;r*(|/viiii\n" +
                "+>>r\"r=;r\"\"\"/viiv]iiv\\)uWRWRH{```. 'kRW0q31v>. -.>@F..'.CN .`.?Hr.`..Oz `'.VR?'' 'MU ..-iQ| '  ZRQHBQRQQggRQBgHWB&|_+>>>;*\"**)|??vvi7\n" +
                "<+++<r=;;?|(viiviiivi(i@BHgHWi `    fgQ1!' ~y:  -+Du..` JD.   /D* ..-Oy- .-aB?` --Nb   'vg| '  PBRHHRgQBHQQgBgHRBWZ+><<>*;r\"*\\|?)ivvi\n" +
                ">>>>>+*;;(|((vivvvvi)(jRQWRR$` -_ `-~OO!` `{B= .'+Nj-.`'jM   -?&=  ` Mz-` `4g?` ..Db..  vW\\'`-'wBRQHRQBggRWWBQBRHHN?<>>+\"\"=;=?)(/vii7\n" +
                "><+<+<+;=\"\"=(?\\\\\\i\\(;?MBBgWgr``:Er ' cM<'. *V,  .>NJ--  vT.'. vR}.- '1( .'_OQ\\` '-;<--'`,*--..`%meWRHQWWRWgHgQgRQQWs>>+<;;=;\")||(vivx\n" +
                "::!,~!<<<**\"=\"\"r\"(\"=*yWWRHg6><>lgF<><<Gh?_`,l\\>>>|&2- . ii<._vMOMf/>^`:+;fMHH{>+>><+<>++++<<+><%w JRQBBBWWQHHWRWBBgZ,>><<=;**/?|?iivx\n" +
                ",~^!^,^_<>+<+>+>+<<<;MQBBQHHHQgRQHRW$HHQHgRWRQBgRWBz ` 'eWgBQW8*aOM&NQHHHgQBgWgRgQQWQWRgWgRRRRWgM~vWWWBWQWQWQBQBWgRX^>++\"r;*r|/\\vii[c\n" +
                "_^^::,!~,_+><>+>+<+_1RBWQWgQBQBWHWQpYBBHWQRRRQQRWBR&MOMO&HgBgHOYvEqqUDHQ&&HgHQHBWQWBHWWRBWBRBHHBQ|`CWBgQBgRHgQRWQHQ$~<<\"=r=\")?(?ivvl]\n" +
                "!^~:::__,~~^~!:+>:!!KHRBHWBgBBWRHHW[PHRRRHBHgHBWQRWQWggHRQBHgRNdiV8pbDBgDDHRgRQQggRRBHHRBWRQHQWWQY'`vqQggWRBWBQWgWHX,>>*\"=;?)?/ivicL}\n" +
                "_:^,,^_!_:::~:^^_!-?BHQWQHBRRQBQHB$cBRHBHgHHHgRRgHQWHBBBRRRBWHNdZhTCGMQW@@gHQWRHQWBBHBQgHBQWgHQQQ3 '_]NQWBHQHHQRggWZ:>=*\"=(/\\iivixL[1\n" +
                "!,!!,!^~,^,~^_!~,^,yBWRQQQBQHHQWHD(ERQQBRRBgBRRQHQWQHQRQgWRBRB@G0dAte3kg@MBWQQQHRgWQRBHgWgHBWRQWBb+_lggRgRggQg https://asciify.me YTt\n" +
                "_:,_:^^:_^!:!,,~:^^PQHgHWHHgWHHBgoYWQgQBQBWgBQRBWQHWBRBgHgBWWHWMdU8qSXMWOdRWQRHHRQQRBRHRHgBWBQRHH&*<cBBQgBBgHQHBRggI+<;;|||vvv[c1Y1yt";


        response.msg(harosh).status(Status.OK);
        return response;
    }

}
