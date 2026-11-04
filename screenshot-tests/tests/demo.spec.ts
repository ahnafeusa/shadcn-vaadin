import { expect, test, type Page } from '@playwright/test';

async function openDemo(page: Page) {
  await page.goto('/');
  const grid = page.locator('vaadin-grid');
  // The first request after start compiles the dev bundle, which can take a while.
  await grid.waitFor({ timeout: 180_000 });
  await grid.locator('vaadin-grid-cell-content', { hasText: 'INV-0006' }).waitFor();
  // Vite's dependency optimiser can force a reload shortly after the first visit; let it settle.
  await page.waitForLoadState('networkidle');
  await page.addStyleTag({ content: 'vaadin-dev-tools { display: none !important; }' });
  await page.evaluate(() => document.fonts.ready);
  await page.waitForTimeout(500);
}

test('light mode', async ({ page }) => {
  await openDemo(page);
  await expect(page).toHaveScreenshot('light.png', { fullPage: true });
});

test('dark mode', async ({ page }) => {
  await openDemo(page);
  await page.getByRole('button', { name: 'Toggle theme' }).click();
  await expect(page.locator('html')).toHaveCSS('color-scheme', 'dark');
  await page.waitForTimeout(300);
  await expect(page).toHaveScreenshot('dark.png', { fullPage: true });
});

test('dialog', async ({ page }) => {
  await openDemo(page);
  await page.getByRole('button', { name: 'Open dialog' }).click();
  await page.locator('vaadin-dialog-overlay[opened]').waitFor();
  await page.waitForTimeout(300);
  await expect(page).toHaveScreenshot('dialog.png');
});

test('combo box overlay', async ({ page }) => {
  await openDemo(page);
  await page.locator('vaadin-combo-box').click();
  await page.locator('vaadin-combo-box-overlay[opened]').waitFor();
  await page.waitForTimeout(300);
  await expect(page).toHaveScreenshot('combo-box.png');
});

test('focused text field', async ({ page }) => {
  await openDemo(page);
  const inputs = page.locator('.demo__grid').first();
  await inputs.locator('vaadin-text-field').first().locator('input').click();
  await page.waitForTimeout(300);
  await expect(inputs).toHaveScreenshot('focused-field.png');
});

test('hovered buttons', async ({ page }) => {
  await openDemo(page);
  const row = page.locator('.demo__row').first();
  await row.getByRole('button', { name: 'Outline' }).hover();
  await page.waitForTimeout(300);
  await expect(row).toHaveScreenshot('hovered-outline-button.png');
  await row.getByRole('button', { name: 'Primary' }).hover();
  await page.waitForTimeout(300);
  await expect(row).toHaveScreenshot('hovered-primary-button.png');
});
