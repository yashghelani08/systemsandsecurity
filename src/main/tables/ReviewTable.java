package main.tables;
import java.sql.*;
import java.util.ArrayList;

public class ReviewTable {

    public static void main(String args[]) throws SQLException {

        ReviewTable rt = new ReviewTable();
        rt.CreateReviewTable();
        //vt.Insert(12345678, 2018);
    }

    public static void CreateReviewTable() throws SQLException {

        Connection con = null; // a Connection object
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            //=========================================================================================================

            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String jtable = "CREATE TABLE Review " + //Creating the table "UserTable"
                        "(ReviewID              INT     AUTO_INCREMENT, " + //Creating the different fields
                        "ReviewerID             INT, " +
                        "ArticleID               INT," +
                        "Summary                TEXT, " +
                        "InitialVerdict         TEXT, " +
                        "FinalVerdict           TEXT," +
                        "PRIMARY KEY (ReviewID), " +
                        "FOREIGN KEY (ReviewerID) REFERENCES Reviewer(ReviewerID), " +
                        "FOREIGN KEY (ArticleID) REFERENCES Articles(ArticleID))";
                stmt.executeUpdate(jtable);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
            //=========================================================================================================
        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            if (con != null) con.close(); }
    }
    public static void Insert(int reviewerid, int articleid, String Summary, String initialverdict, String finalverdict) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "INSERT INTO Review (ReviewerID, ArticleID, Summary, InitialVerdict, FinalVerdict) VALUES (" + reviewerid + "," + articleid + ", '" + Summary + "', '" + initialverdict + "', '" + finalverdict + "')";
                System.out.println(journal);
                stmt.executeUpdate(journal);

            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }

    public static void Delete(int reviewid) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "DELETE FROM Review WHERE ReviewID = " + reviewid;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }


    public static void UpdateSummary(String Summary) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Review SET Summary = '" + Summary + "'";
                //System.out.println(journal);
                stmt.executeUpdate(journal);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }

    public static void UpdateVerdict(String verdict) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Review SET verdict = '" + verdict + "'";
                //System.out.println(journal);
                stmt.executeUpdate(journal);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }

    public static void UpdateReviewerID(int id) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Review SET ReviewerID = " + id;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }

    public static void UpdateSubmissionInfoID(int id) throws SQLException {
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String journal = "UPDATE Review SET SubmissionInfoID = " + id;
                //System.out.println(journal);
                stmt.executeUpdate(journal);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }

    public int SelectReviewerID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT ReviewerID FROM Review WHERE ReviewID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("ReviewerID");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }

    public int SelectSubmissionInfoID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT SubmissionInfoID FROM Review WHERE ReviewID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("SubmissionInfoID");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }

    public static String SelectSummary(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Summary FROM Review WHERE ReviewID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getString("Summary");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }

    public static String SelectVerdict(int id) throws SQLException {
        String fin = null;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT Verdict FROM Review WHERE ReviewID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getString("Verdict");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }


    public static void DeleteTable() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String newEdition = "DROP TABLE Review";
                stmt.executeUpdate(newEdition);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
    }

    public static int SelectReviewID(int reviewerid, int submissionid, String summary, String verdict) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT ReviewID FROM Review WHERE ReviewerID = " + reviewerid + " AND SubmissionID = " + submissionid + " AND Summary = '" + summary + "' AND Verdict = '" + verdict + "'";
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    fin = res.getInt("ReviewID");
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }

    public static ArrayList<Integer> SelectListOfArticleIDs(int id) throws SQLException {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT ArticleID FROM Review WHERE ReviewerID = " + id + " AND Summary = 'null' AND InitialVerdict = 'null' AND FinalVerdict = 'null'";
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    int fin = res.getInt("ArticleID");
                    list.add(fin);
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return list;
    }

    public static int CheckReviewID(int id) throws SQLException {
        int fin = 0;
        Connection con = null; // connection to a database
        try {
            con = DriverManager.getConnection("jdbc:mysql://stusql.dcs.shef.ac.uk/team044", "team044", "f1e121fa");
            // use the open connection
            Statement stmt = null;
            try {
                stmt = con.createStatement();
                String query = "SELECT ReviewID FROM Review WHERE ArticleID = " + id;
                ResultSet res = stmt.executeQuery(query);
                while (res.next()) {
                    int dn = res.getInt("ReviewID");
                    fin++;
                }
                res.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                if (stmt != null)
                    stmt.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) con.close();
        }
        return fin;
    }
}