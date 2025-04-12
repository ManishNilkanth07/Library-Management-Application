package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import com.management.library_management_system.model.Reservation;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    public boolean isReservedByAnotherUser(int bookId, int currentStudentId) {
        String sql = "SELECT COUNT(*) FROM reservation WHERE book_id = ? AND student_id <> ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, currentStudentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createReservation(int studentId, int bookId) {
        String sql = "INSERT INTO reservation (student_id, book_id, created_at) VALUES (?, ?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, bookId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Reservation> getReservationsByStudentId(int studentId) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE student_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation res = new Reservation.ReservationBuilder()
                        .id(rs.getInt("id"))
                        .studentId(rs.getInt("student_id"))
                        .bookId(rs.getInt("book_id"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();

                list.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean deleteReservation(int reservationId) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean hasAlreadyReserved(int studentId, int bookId) {
        String sql = "SELECT COUNT(*) FROM reservation WHERE student_id = ? AND book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            stmt.setInt(2, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Reservation> getReservationsByBookId(int returnedBookId) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservation WHERE book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, returnedBookId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reservation res = new Reservation.ReservationBuilder()
                        .id(rs.getInt("id"))
                        .studentId(rs.getInt("student_id"))
                        .bookId(rs.getInt("book_id"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();

                list.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
