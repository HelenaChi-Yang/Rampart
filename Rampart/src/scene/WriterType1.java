package scene;

import com.company.GameKernel;
import com.company.Path;
import controllers.ImageResourceController;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WriterType1 implements GameKernel.GameInterface, Runnable{
    // 最大对话数
    private int _MAX_COUNT = 10;

    //间隔标记
    private char _flag='|';

    private Image _screen;

    private Graphics _bg;

    private Thread _L_WriterType;

    private static Color _L_fClr;

    private static Color _L_bClr;

    private static String _M_Text[];

    private int _S_COUNT;

    private Font _L_TTF;

    private int _L_Speed;

    private int _L_Delay;

    private String _C_Text[];

    private static int _L_COUNT;

    private static int _F_COUNT;

    private static int _T_COUNT;

    private static int _N_Text;

    private String _FontName;

    private final static int _WIDTH = 500;

    private final static int _HEIGHT = 375;

    private static int _FontStyle;

    private static int _FontSize;

    private static  Image _back = null;

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private boolean _isRun;

    /**
     * 析构函数,注入背景图,循环显示的文字数组,速度,延迟
     * @param img
     * @param strContent
     * @param speed
     * @param delay
     */
    public WriterType1(Image img,String[] strContent, int speed, int delay) {
        init(img,strContent, speed, delay);
    }

    private WriterType1() {

    }

    public void init(Image img,String[] strContent, int speed, int delay) {
        //初始背景色
//        setBackground(Color.BLACK);
        // 原始对话信息
        _M_Text = new String[_MAX_COUNT];
        // 缓存对话信息
        _C_Text = new String[_MAX_COUNT];

        _back=img;
        _FontName = "宋体";

        _FontStyle = 0;

        _FontSize = 15;

        _L_TTF = new Font(_FontName, _FontStyle, _FontSize);
        _L_bClr = Color.BLACK;
        _L_fClr = new Color(250, 250, 250);

        int length = strContent.length;

        // 循环次数
        _N_Text = strContent.length - 1;

        // 直接copy String数组
        System.arraycopy(strContent, 0, _M_Text, 0, length);

        //速度及延迟设定
        _L_Speed = speed;
        _L_Delay = delay;

        //缓存区域
        _screen = new BufferedImage(_WIDTH, _HEIGHT, 1);
        _bg = _screen.getGraphics();

        //开启线程
        _isRun = true;
        _L_WriterType = new Thread(this);
        _L_WriterType.start();

    }

    /**
     * 停止线程
     *
     */
    public void stop() {
        _S_COUNT = 0;
        _L_COUNT = 0;
        _F_COUNT = 0;
        if (_L_WriterType != null) {
            _isRun = false;
            _L_WriterType = null;
        }
    }

    public void paintComponent(Graphics g){
        _bg.setColor(_L_bClr);
        _bg.fillRect(0, 0, _WIDTH, _HEIGHT);
        _bg.setColor(_L_fClr);
        _bg.setFont(_L_TTF);
        _bg.drawImage(_back, 0, 0, null);
        for (int i = 0; i <= _L_COUNT; i++) {
            //转换当前字体top位置
            Double top = new Double((double) _FontSize
                    * (3.0D + 2.0D * (double) i));
            if (_C_Text[i] != null) {
                _bg.drawString(_C_Text[i], 50, top.intValue());
            }
        }

        g.drawImage(_screen, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        paintComponent(g);
    }

    @Override
    public void update() {

    }

    @Override
    public void run() {
        while (_isRun) {
            try {

                //字符串累加
                _S_COUNT++;
                //当字符串完成整段对话时
                if (_S_COUNT == _M_Text[_T_COUNT].length()) {
                    _C_Text[_L_COUNT] = _M_Text[_T_COUNT].substring(_F_COUNT,
                            _S_COUNT);
                    begin();
                    Thread.sleep(_L_Delay);
                    //repaint();
                }
                //比对标识设定缓存对话
                if (_M_Text[_T_COUNT].charAt(_S_COUNT) != _flag) {
                    _C_Text[_L_COUNT] = _M_Text[_T_COUNT].substring(_F_COUNT,
                            _S_COUNT)
                            + "_";
                } else {
                    _C_Text[_L_COUNT] = _M_Text[_T_COUNT].substring(_F_COUNT,
                            _S_COUNT);
                }
                //用以分辨是否转化对话组时的不同延迟
                boolean shortSleep;
                //当'|'标识存在时执行
                if (_M_Text[_T_COUNT].charAt(_S_COUNT) == _flag) {
                    _L_COUNT++;
                    _S_COUNT++;
                    _F_COUNT = _S_COUNT;
                    shortSleep = true;
                } else {
                    shortSleep = false;
                }
                //刷新
                //repaint();
                //判断速度
                if (!shortSleep) {
                    Thread.sleep(_L_Speed);
                } else {
                    Thread.sleep(10 * _L_Speed);
                }
            } catch (InterruptedException e) {
                stop();
            }
        }
    }

    private void begin() {
        //清除所有缓存数据。
        for (int i = 0; i < _C_Text.length; i++) {
            _C_Text[i] = null;
        }
        _F_COUNT = 0;
        _S_COUNT = 0;
        _L_COUNT = 0;
        _T_COUNT++;
        if (_T_COUNT > _N_Text) {
            _T_COUNT = 0;
        }
    }

    public int getMaxCount() {
        return _MAX_COUNT;
    }

    public void setMaxCount(int max_count) {
        _MAX_COUNT = max_count;
    }


//    public static void main(String[] args) {
//        String[] abracadabra = new String[] {
//                "身为剑体,|钢铁之血灼璃之心.|手创千剑,身经百战;|无一败绩,无一知己.|常一人自醉于剑丘之巅;|此生亦无任何意义;|故此身，定为无限剑成;|故此命，定为无限剑制.",
//                "I am the bone of my sword;|Steel is my body, and fire is my blood.|I have created over a thousand blades.|Have withstood pain to create many weapons.|Yet,those hands will never hold anything.|So as I pray, unlimited blade works." };
//        Frame frm = new Frame();
//        frm.setTitle("Java图形程序中的对话逐字显示(由Loonframework提供)");
//        frm.setSize(_WIDTH, _HEIGHT + 20);
//        frm.setResizable(false);
//        frm.add(new WriterType(new ImageResourceController().tryGetImage(new Path().img().backgrounds().main()),abracadabra,50,5000));
//        frm.setVisible(true);
//    }
}



