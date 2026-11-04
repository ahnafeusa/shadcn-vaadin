import { defineConfig, devices } from '@playwright/test';
import path from 'node:path';

const repoRoot = path.resolve(__dirname, '..');
const mvnw = `"${path.join(repoRoot, process.platform === 'win32' ? 'mvnw.cmd' : 'mvnw')}"`;

export default defineConfig({
  testDir: './tests',
  fullyParallel: false,
  workers: 1,
  retries: 0,
  reporter: process.env.CI ? [['github'], ['html', { open: 'never' }]] : [['list']],
  expect: {
    toHaveScreenshot: {
      // Antialiasing jitter between runs on the same image; anything visible is far above this.
      maxDiffPixels: 64,
      animations: 'disabled',
      caret: 'hide',
    },
  },
  use: {
    ...devices['Desktop Chrome'],
    baseURL: 'http://localhost:8080',
    viewport: { width: 1280, height: 900 },
    deviceScaleFactor: 1,
    colorScheme: 'light',
  },
  projects: [{ name: 'chromium' }],
  webServer: {
    command: `${mvnw} -B -ntp jetty:run`,
    // Maven resolves the POM from the working directory, not from the script's location.
    cwd: repoRoot,
    url: 'http://localhost:8080/',
    // First start in CI downloads Node and runs npm install for the Vaadin dev bundle.
    timeout: 300_000,
    reuseExistingServer: !process.env.CI,
    // Maven reports failures on stdout; keep it visible in CI, quiet locally.
    stdout: process.env.CI ? 'pipe' : 'ignore',
    stderr: 'pipe',
  },
});
