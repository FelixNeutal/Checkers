using CLIMenuSystem;

namespace Checkers;

public class Program
{
    public static void Main(String[] args)
    {
        Menu primaryMenu = new Menu("First Menu", MenuLevel.FIRST);
        //primaryMenu.AddMenuItem(new MenuItem("A", "Alpha", AlphaFunction));
        primaryMenu.AddMenuItem(new MenuItem("B", "Bravo", BravoFunction));
        primaryMenu.Run();
    }

    public static string AlphaFunction()
    {
        Console.WriteLine("Doing something important in alphafunction");
        return "";
    }
    
    public static string BravoFunction()
    {
        Menu bravoMenu = new Menu("Second menu", MenuLevel.SECOND);
        //bravoMenu.AddMenuItem(new MenuItem("c", "Charlie", CharlieFunction));
        bravoMenu.AddMenuItem(new MenuItem("d", "Delta", DeltaFunction));
        return bravoMenu.Run();
        //return "";
    }
    
    public static string CharlieFunction()
    {
        Console.WriteLine("Inside Charlie function doing something");
        return "";
    }
    
    public static string DeltaFunction()
    {
        Menu deltaMenu = new Menu("Third menu", MenuLevel.SECOND);
        deltaMenu.AddMenuItem(new MenuItem("f", "Echo", EchoFunction));
        return deltaMenu.Run();
        //return "";
    }

    public static string EchoFunction()
    {
        return "";
    }
}