package jp.co.kiramex.dbkadai.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Review05 {

    public static void main1(String[] args) {
        // 3. データベース接続と結果取得のための変数宣言
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. DBと接続する
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "Gorbej-2hokho-jecfyq"
                        );

                // 4.Dbとやりとりする窓口（PreparedStatementオブジェクト）の作成
                String sql = "SELECT * FROM id WHERE age = ?";
                pstmt = con.prepareStatement(sql);
                //5, 6. Select文の実行と結果を格納／代入
                System.out.print("検索キーワードを入力してください > ");
                String input = keyIn();
             // PreparedStatementオブジェクトの?に値をセット  // ← 追記
                pstmt.setInt(1, input);  // ← 追記

                rs = pstmt.executeQuery();  // ← 修正

                // 7. 結果を表示する
                while(rs.next()) {
                    // Name列の値を取得
                    int id = rs.getInt("id");
                    int age = rs.getInt("age");
                    //取得した値を表示
                    System.out.println(id);
                    System.out.println(age);
                }
            } catch (ClassNotFoundException e) {
                System.err.println("review05ドライバーのロードに失敗しました。");
                e.printStackTrace();
            }catch (SQLException e) {
                System.err.println("データベースに異常が発生しました。");
                e.printStackTrace();
            } finally {
                // 8. 接続を閉じる
                if( con != null ){
                    try {
                        con.close();
                    } catch (SQLException e) {
                        System.err.println("データベース切断時にエラーが発生しました。");
                        e.printStackTrace();
                    }
                }
            }

        }
    /*
     * キーボードから入力された値をStringで返す 引数：なし 戻り値：入力された文字列
     */
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(System.in));
            line = key.readLine();
        } catch (IOException e) {

        }
        return line;
    }

}
