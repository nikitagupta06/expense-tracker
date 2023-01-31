package com.nikitagupta06.springbootmongodbtutorial.service;

import com.nikitagupta06.springbootmongodbtutorial.model.Expense;
import com.nikitagupta06.springbootmongodbtutorial.repository.ExpenseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService  {

    private ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void addExpense(Expense expense) {
        expenseRepository.insert( expense);
    }
    public void updateExpense(Expense expense) {
        Expense savedExpense = expenseRepository.findById(expense.getId())
                .orElseThrow(()-> new RuntimeException(
                        String.format("Cannot find expense by ID %s", expense.getId())
                ));
        savedExpense.setExpenseName(expense.getExpenseName());
        savedExpense.setExpenseCategory(expense.getExpenseCategory() );
        savedExpense.setExpenseAmount(expense.getExpenseAmount());

        expenseRepository.save(expense);
    }
    public ResponseEntity<List<Expense>> getAllExpenses() {
        return ResponseEntity.ok(expenseRepository.findAll());
    }
    public Expense getExpenseByName(String name) {
        return expenseRepository.findByName(name)
                .orElseThrow(()-> new RuntimeException(
                        String.format("Cannot find expense by name %s", name)
                ));
    }
    public void deleteExpense(String id) {
        expenseRepository.deleteById(id);
    }
}
